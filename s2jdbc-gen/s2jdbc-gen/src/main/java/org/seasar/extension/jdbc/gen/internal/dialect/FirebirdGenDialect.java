/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.gen.internal.dialect;

import javax.persistence.GenerationType;

/**
 * Firebirdの方言を扱うクラスです。
 * 
 * @author taedium
 */
public class FirebirdGenDialect extends StandardGenDialect {

    /**
     * インスタンスを構築します。
     */
    public FirebirdGenDialect() {
    }

    @Override
    public GenerationType getDefaultGenerationType() {
        return GenerationType.SEQUENCE;
    }

    @Override
    public String getSequenceNextValString(String sequenceName,
            int allocationSize) {
        return "select gen_id( " + sequenceName + ", " + allocationSize
                + " ) from RDB$DATABASE";
    }

}