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
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.SequenceGenerator

import org.ekolandia.dispatch.loaddata.Constants;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@ToString
@Entity
class Food extends BaseEntity implements Serializable {

    private static final long serialVersionUID = Constants.VERSION;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOOD_SEQ")
    @SequenceGenerator(name = "FOOD_SEQ", sequenceName = "FOOD_SEQ", allocationSize = 1)
    Long id

    @Column(nullable = false)
    Long sortId

    @Column(nullable = false, length = 1000)
    String name

    @ManyToMany
    @JoinTable(name="FOOD_MATERIAL", joinColumns=
        @JoinColumn(name="FOOD_ID", referencedColumnName="ID"), inverseJoinColumns=
        @JoinColumn(name="MATERIAL_ID", referencedColumnName="ID"))
    List<Material> materials = new ArrayList<Material>();
}
