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

import static org.junit.Assert.*

import javax.annotation.Resource

import org.ekolandia.dispatch.loaddata.ApplicationEntryPoint
import org.ekolandia.dispatch.loaddata.dto.ClientDTO
import org.ekolandia.dispatch.loaddata.repository.ImportDataRepository;
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = ApplicationEntryPoint)
class ImportServiceTest extends Specification {
    
    @Resource
    ImportService importService
    
    @Resource
    ImportDataRepository importDataRepository
    
    def "test import client data"() {
        when:
            importService.importClient(new ClientDTO())
        then:
            def findAll = importDataRepository.findAll();
            findAll.size() > 0
    }
}