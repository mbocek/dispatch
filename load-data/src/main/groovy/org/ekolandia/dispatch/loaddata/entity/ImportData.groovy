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
package org.ekolandia.dispatch.loaddata.entity

import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator

import org.ekolandia.dispatch.loaddata.Constants;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@ToString
@Entity
class ImportData implements Serializable {

    private static final long serialVersionUID = Constants.VERSION;

    public static enum State {
        SUCCEED, FAILED;
    }

    public static enum DataType {
        CLIENT, MATERIAL, FOOD;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMPORT_DATA_SEQ")
    @SequenceGenerator(name = "IMPORT_DATA_SEQ", sequenceName = "IMPORT_DATA_SEQ", allocationSize = 1)
    Long id

    @Column(nullable = false)
    Date lastImported

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true)
    State state;
    
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true)
    DataType dataType;
}
