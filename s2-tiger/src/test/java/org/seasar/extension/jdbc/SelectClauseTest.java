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
package org.seasar.extension.jdbc;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class SelectClauseTest extends TestCase {

    /**
     * 
     */
    public void testAddSql() {
        SelectClause selectClause = new SelectClause();
        selectClause.addSql("T1_", "AAA_ID");
        assertEquals("select T1_.AAA_ID", selectClause.toSql());
        selectClause.addSql("T1_", "AAA_NAME");
        assertEquals("select T1_.AAA_ID, T1_.AAA_NAME", selectClause.toSql());
    }

    /**
     * 
     */
    public void testGetLength() {
        SelectClause selectClause = new SelectClause();
        assertEquals(0, selectClause.getLength());
        selectClause.addSql("T1_", "AAA_ID");
        assertEquals("select T1_.AAA_ID".length(), selectClause.getLength());
    }
}