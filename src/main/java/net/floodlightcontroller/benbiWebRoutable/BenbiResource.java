package net.floodlightcontroller.benbiWebRoutable;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jessy on 2017/12/2.
 */
public class BenbiResource extends ServerResource {
    protected static Logger log = LoggerFactory.getLogger(BenbiResource.class);

    @Post
    public void filter(String json){
        //decrypt
        //dispatcher
        System.out.println("------filter-------" + json);
    }



}
