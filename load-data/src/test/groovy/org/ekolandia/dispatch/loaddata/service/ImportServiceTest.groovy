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

import org.ekolandia.dispatch.loaddata.dto.FoodDTO
import org.ekolandia.dispatch.loaddata.repository.FoodRepository

import javax.transaction.Transactional

import static org.junit.Assert.*

import javax.annotation.Resource

import org.ekolandia.dispatch.loaddata.ApplicationEntryPoint
import org.ekolandia.dispatch.loaddata.dto.ClientDTO
import org.ekolandia.dispatch.loaddata.dto.MaterialDTO;
import org.ekolandia.dispatch.loaddata.repository.ClientRepository
import org.ekolandia.dispatch.loaddata.repository.MaterialRepository;
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Transactional
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = ApplicationEntryPoint)
class ImportServiceTest extends Specification {
    
    @Resource
    ImportService importService
    
    @Resource
    ClientRepository clientRepository
    
    @Resource
    MaterialRepository materialRepository

    @Resource
    FoodRepository foodRepository

    def "test import client data"() {
        when:
            def data = new ArrayList<ClientDTO>()
            data << new ClientDTO(code: "test1", name: "Test Subject", category: "A01", groupId: 1)
            data << new ClientDTO(code: "test2", name: "Test Subject2", category: "A01", groupId: 1)
            importService.importClient(data)
        then:
            def findAll = clientRepository.findAll();
            assert findAll.size() == 2
    }

    def "test import material data"() {
        when:
            def data = new ArrayList<MaterialDTO>()
            data << new MaterialDTO(code: "test1", name: "Test Subject", totalWeight: 100, meatWeight: 80)
            data << new MaterialDTO(code: "test2", name: "Test Subject2", totalWeight: 100, meatWeight: 80)
            importService.importMaterial(data)
        then:
            def findAll = materialRepository.findAll();
            assert findAll.size() == 2
    }

    def "test import food"() {
        when:
        def data = new ArrayList<MaterialDTO>()
        data << new MaterialDTO(code: "test1", name: "Test Subject", totalWeight: 100, meatWeight: 80)
        data << new MaterialDTO(code: "test2", name: "Test Subject2", totalWeight: 100, meatWeight: 80)
        importService.importMaterial(data)
        def food = new ArrayList<FoodDTO>()
        food << new FoodDTO(sortId: 1, name: "Food 1", materialCodes: ["test1"])
        food << new FoodDTO(sortId: 2, name: "Food 2", materialCodes: ["test1", "test2"])
        importService.importFood(food)
        then:
        def findAll = foodRepository.findAll();
        findAll.size() == 2
        findAll.getAt(0).materials.size() == 1
        findAll.getAt(1).materials.size() == 2
    }
}
