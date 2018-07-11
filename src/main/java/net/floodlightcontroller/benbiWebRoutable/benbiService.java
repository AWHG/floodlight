package net.floodlightcontroller.benbiWebRoutable;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.restserver.IRestApiService;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by jessy on 2017/12/2.
 */
public class benbiService implements IFloodlightModule, IOFMessageListener {

    protected static Logger log = LoggerFactory.getLogger(benbiService.class);

    public static final String MODULE_NAME = "benbiWebRoutable";

    protected IRestApiService restApiService;
    protected static final String PACKAGE = benbiService.class.getPackage().getName();


    @Override
    public Collection<Class<? extends IFloodlightService>> getModuleServices() {
        return null;
    }

    @Override
    public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
        return null;
    }

    @Override
    public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
        Collection<Class<? extends IFloodlightService>> l =
                new ArrayList<Class<? extends IFloodlightService>>();
        l.add(IRestApiService.class);
        return l;
    }

    @Override
    public void init(FloodlightModuleContext context) throws FloodlightModuleException {

        restApiService = context.getServiceImpl(IRestApiService.class);


    }

    @Override
    public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
        System.out.println("-----------benbiService-----------");
        restApiService.addRestletRoutable(new BenbiWenRoutable());
    }


    @Override
    public Command receive(IOFSwitch sw, OFMessage msg, FloodlightContext cntx) {
        return null;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }
    @Override
    public boolean isCallbackOrderingPrereq(OFType type, String name) {
        return false;
    }

    @Override
    public boolean isCallbackOrderingPostreq(OFType type, String name) {
        return false;
    }
}

