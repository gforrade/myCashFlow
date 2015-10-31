/**
 * Created by gforrade on 7/12/15.
 * Copyright (c) 2015, GAF S.A.
 */
package ar.com.gaf.mycashflow.dao;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

/**
 *
 * @version $Id$
 */
public class ContainerManager {
    private static ContainerManager manager=null;
    public static ContainerManager getInstance(){
        if(manager==null)
            manager = new ContainerManager();
        return manager;
    }
    
    private EJBContainer container=null;
    
    /*
         * Start embedded glassfish server
         */
    private ContainerManager(){
        
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
                "./target/test-classes/glassfish");
        container = EJBContainer.createEJBContainer(properties);
       
    }
    /*
                * Obtain references to beans under test.
                */
    public Context getContext(){
        return container.getContext();
    }
    public void closeContext() throws Exception{
        container.getContext().close();
    }
    
}
