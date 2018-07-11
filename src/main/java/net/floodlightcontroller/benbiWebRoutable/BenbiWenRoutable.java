package net.floodlightcontroller.benbiWebRoutable;

import net.floodlightcontroller.restserver.RestletRoutable;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Created by jessy on 2017/12/2.
 */
public class BenbiWenRoutable implements RestletRoutable {
    @Override
    public Restlet getRestlet(Context context) {
        System.out.println("-------benbiResource----------");
        Router router = new Router(context);
        router.attach("/json", BenbiResource.class);
        return router;
    }

    @Override
    public String basePath() {
        return "/wm";
    }
}
