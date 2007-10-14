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
package org.seasar.extension.jdbc.dialect;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.extension.jdbc.FromClause;
import org.seasar.extension.jdbc.JoinColumnMeta;
import org.seasar.extension.jdbc.JoinType;

/**
 * @author higa
 * 
 */
public class StandardDialectTest extends TestCase {

    private StandardDialect dialect = new StandardDialect();

    /**
     * @throws Exception
     */
    public void testConvertLimitSqlByRowNumber_offsetLimit() throws Exception {
        String sql = "select * from emp order by id";
        String expected = "select * from ( select "
                + "temp_.*, row_number() over(order by id) as rownumber_ from ( select * from emp ) as temp_ ) as temp2_"
                + " where rownumber_ >= 6 and rownumber_ <= 15";
        assertEquals(expected, dialect.convertLimitSqlByRowNumber(sql, 5, 10));
    }

    /**
     * @throws Exception
     */
    public void testConvertLimitSqlByRowNumber_offsetLimit_tableAlias()
            throws Exception {
        String sql = "select * from emp T1_ order by T1_.id";
        String expected = "select * from ( select "
                + "temp_.*, row_number() over(order by temp_.id) as rownumber_ from ( select * from emp T1_ ) as temp_ ) as temp2_"
                + " where rownumber_ >= 6 and rownumber_ <= 15";
        assertEquals(expected, dialect.convertLimitSqlByRowNumber(sql, 5, 10));
    }

    /**
     * @throws Exception
     */
    public void testConvertLimitSqlByRowNumber_offsetOnly() throws Exception {
        String sql = "select * from emp order by id";
        String expected = "select * from ( select "
                + "temp_.*, row_number() over(order by id) as rownumber_ from ( select * from emp ) as temp_ ) as temp2_"
                + " where rownumber_ >= 6";
        assertEquals(expected, dialect.convertLimitSqlByRowNumber(sql, 5, 0));
    }

    /**
     * @throws Exception
     */
    public void testConvertOrderBy() throws Exception {
        assertEquals("order by id", dialect.convertOrderBy("order by id"));
        assertEquals("order by temp_.id", dialect
                .convertOrderBy("order by T1_.id"));
    }

    /**
     * 
     */
    public void testSetupJoin() {
        StandardDialect dialect = new StandardDialect();
        FromClause fromClause = new FromClause();
        fromClause.addSql("AAA", "_T1");
        List<JoinColumnMeta> joinColumnMetaList = new ArrayList<JoinColumnMeta>();
        joinColumnMetaList.add(new JoinColumnMeta("BBB_ID", "BBB_ID"));
        dialect.setupJoin(fromClause, null, JoinType.LEFT_OUTER, "BBB", "_T2",
                "_T1", "_T2", joinColumnMetaList);
        assertEquals(
                " from AAA _T1 left outer join BBB _T2 on _T1.BBB_ID = _T2.BBB_ID",
                fromClause.toSql());
    }

}