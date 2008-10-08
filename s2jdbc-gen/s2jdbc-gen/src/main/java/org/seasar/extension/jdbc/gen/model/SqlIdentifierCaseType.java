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
package org.seasar.extension.jdbc.gen.model;

/**
 * SQLの識別子の大文字小文字を変換するかどうかを示す列挙型です。
 * 
 * @author taedium
 */
public enum SqlIdentifierCaseType {

    /** 大文字 */
    UPPERCASE {

        @Override
        public String convert(String identifier) {
            return identifier != null ? identifier.toUpperCase() : null;
        }
    },

    /** 小文字 */
    LOWERCASE {

        @Override
        public String convert(String identifier) {
            return identifier != null ? identifier.toLowerCase() : null;
        }
    },

    /** 元のまま */
    ORIGINALCASE {

        @Override
        public String convert(String identifier) {
            return identifier;
        }
    };

    /**
     * 識別子の大文字小文字を変換します。
     * 
     * @param identifier
     *            識別子
     * @return 変換された文字列
     */
    public abstract String convert(String identifier);
}