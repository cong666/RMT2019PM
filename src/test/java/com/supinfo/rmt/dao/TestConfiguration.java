/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.dao;

import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import org.junit.Before;

/**
 *
 * @author ccong
 */
public class TestConfiguration {
    
    private EJBContainer container;

    @Before
    public void init() {
        Properties properties = new Properties();
        //props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        //InitialContext ctx = new InitialContext(props);
        //AnimalService animalService = (AnimalService) ctx.lookup("AnimalService");
        properties.put("org.glassfish.ejb.embedded.glassfish.configuration.file",
                "/Applications/NetBeans/glassfish5/glassfish/domains/domain1/config/domain.xml");

        container = EJBContainer.createEJBContainer(properties);
    }

    public EJBContainer getContainer() {
        return container;
    }

    public void setContainer(EJBContainer container) {
        this.container = container;
    }
    
}
