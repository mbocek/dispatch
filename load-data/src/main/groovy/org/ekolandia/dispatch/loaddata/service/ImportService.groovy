/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ekolandia.dispatch.loaddata.service

import javax.annotation.Resource

import org.ekolandia.dispatch.loaddata.dto.ClientDTO
import org.ekolandia.dispatch.loaddata.dto.MaterialDTO;
import org.ekolandia.dispatch.loaddata.entity.Client;
import org.ekolandia.dispatch.loaddata.entity.ImportData
import org.ekolandia.dispatch.loaddata.entity.Material;
import org.ekolandia.dispatch.loaddata.repository.ClientRepository
import org.ekolandia.dispatch.loaddata.repository.ImportDataRepository
import org.ekolandia.dispatch.loaddata.repository.MaterialRepository;
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import groovy.util.logging.Slf4j;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Slf4j
@Service
class ImportService {

    @Resource
    private ImportDataRepository importDataRepository
    
    @Resource
    private ClientRepository clientRepository 
    
    @Resource
    private MaterialRepository materialRepository 
    
    @Transactional
    def importClient(Collection<ClientDTO> clients) {
        importDataRepository.save(new ImportData(state: ImportData.State.SUCCEED, 
            dataType: ImportData.DataType.CLIENT, lastImported: new Date()))
        
        //merge data
        clientRepository.deactivateAll()
        def clientsDb = clientRepository.findAll()
        Map<String, Client> data = createCodeToClientMap(clientsDb)
        List<Client> result = generateClientList(clients, data)
        clientRepository.save(result)
    }
    
    @Transactional
    def importMaterial(Collection<MaterialDTO> materials) {
        importDataRepository.save(new ImportData(state: ImportData.State.SUCCEED,
            dataType: ImportData.DataType.MATERIAL, lastImported: new Date()))
        
        //merge data
        materialRepository.deactivateAll();
        def materialsDb = materialRepository.findAll()
        Map<String, Material> data = createCodeToMaterialMap(materialsDb)
        List<Material> result = generateMaterialList(materials, data)
        materialRepository.save(result)
    }
    
    private Map<String, Client> createCodeToClientMap(clientsDb) {
        def result = new HashMap<String, Client>()
        clientsDb.each {
            result.put(it.getCode(), it)
        }
        return result
    }
    
    private List<Client> generateClientList(List<ClientDTO> clients, Map<String, Client> data) {
        List<Client> result = new ArrayList<Client>()
        clients.each {
            log.debug("Going to store client: {}", it)
            def client = data.get(it.getCode())
            if (client != null) {
                client.active = true
                client.category = it.category
                client.groupId = it.groupId
                client.lastUpdated = new Date()
                client.name = it.name
            } else {
                client = new Client(it)
            }
            result << client
        }
        return result
    }
    
    private Map<String, Material> createCodeToMaterialMap(materialDb) {
        def result = new HashMap<String, Material>()
        materialDb.each {
            result.put(it.getCode(), it)
        }
        return result
    }
    
    private List<Material> generateMaterialList(List<MaterialDTO> materials, Map<String, Material> data) {
        List<Material> result = new ArrayList<Material>()
        materials.each {
            log.debug("Going to store material: {}", it)
            def material = data.get(it.getCode())
            if (material != null) {
                material.active = true
                material.totalWeight = it.totalWeight
                material.meatWeight = it.meatWeight
                material.lastUpdated = new Date()
                material.name = it.name
                material.code = it.code
            } else {
                material = new Material(it)
            }
            result << material
        }
        return result
    }
 
}
