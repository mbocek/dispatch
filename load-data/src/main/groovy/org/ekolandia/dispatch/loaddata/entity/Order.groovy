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
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

import org.ekolandia.dispatch.loaddata.Constants;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Entity
@Table(name = "ORDER")
@ToString
class Order extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = Constants.VERSION;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FOOD_ID")
    Food food;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLIENT_ID")
    Client client;
    
    @Column(length = 200, nullable = false)
    String category;
    
    @Column(nullable = false)
    Date orderPlacedDate;
    
    @Column(nullable = false)
    Date orderDate;
    
    @Column(nullable = false)
    Integer numberOfOrders;
}
