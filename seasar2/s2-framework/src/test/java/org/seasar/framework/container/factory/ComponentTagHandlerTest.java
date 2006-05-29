/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.deployer.HttpServletComponentDeployerProvider;
import org.seasar.framework.container.impl.HttpServletExternalContext;
import org.seasar.framework.mock.servlet.MockServletContextImpl;

/**
 * @author higa
 * 
 */
public class ComponentTagHandlerTest extends TestCase {

    private static final String PATH = "org/seasar/framework/container/factory/ComponentTagHandlerTest.dicon";

    public void testComponent() throws Exception {
        S2Container container = S2ContainerFactory.create(PATH);
        ComponentDeployerFactory
                .setProvider(new HttpServletComponentDeployerProvider());
        container.init();
        assertNotNull("1", container.getComponent(List.class));
        assertNotNull("2", container.getComponent("aaa"));
        assertEquals("3", new Integer(1), container.getComponent("bbb"));
        assertEquals("4", true, container.getComponent("ccc") != container
                .getComponent("ccc"));
        ComponentDef cd = container.getComponentDef("ddd");
        assertEquals("5", AutoBindingDefFactory.NONE, cd.getAutoBindingDef());
        Map map = new HashMap();
        container.injectDependency(map, "eee");
        assertEquals("6", "111", map.get("aaa"));
        assertNotNull("7", container.getComponent("fff"));
        assertNotNull("8", container.getComponent("ggg"));
        MockServletContextImpl ctx = new MockServletContextImpl("s2jsf-example");
        HttpServletRequest request = ctx.createRequest("/hello.html");
        ExternalContext extCtx = new HttpServletExternalContext();
        extCtx.setRequest(request);
        extCtx.setApplication(ctx);
        container.setExternalContext(extCtx);
        assertNotNull("9", container.getComponent("hhh"));
        assertNotNull("10", container.getComponent("iii"));
        assertEquals("11", "jjj", container.getComponent("jjj"));
        assertEquals("12", "jjj", ctx.getAttribute("jjj"));
    }
}
