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
package org.ekolandia.dispatch.util;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
public final class VersionFormatter {

    private static final String VERSION_SEPATATOR = "."; 
    
    public static String format(Long version) {
        long fix = version % 1000;
        long minor = (version / 1000) % 1000;
        long major = (version / 1000000);

        return major + VERSION_SEPATATOR + minor + VERSION_SEPATATOR + fix;
    }
}
