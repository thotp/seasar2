/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package examples.entity;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author higa
 * 
 */
public class OrderByTest extends S2TestCase {

    private JdbcManager jdbcManager;

    protected void setUp() throws Exception {
        include("app.dicon");
    }

    /**
     * @throws Exception
     */
    public void testOrderBy() throws Exception {
        List<Employee> results =
            jdbcManager
                .from(Employee.class)
                .orderBy("name desc")
                .getResultList();
        for (Employee e : results) {
            System.out.println(e.name);
        }
    }
}