package co.q64.dynamicalsystems.link;

import co.q64.dynamicalsystems.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Singleton
public class LinkManager {
    protected @Inject Logger logger;
    protected @Inject Set<LinkInfo> links;
    //protected @Inject FabricLoader fabricLoader;

    private List<Link> enabledLinks = new ArrayList<>();

    protected @Inject LinkManager() {}

    public void initializeLinks() {
        for (LinkInfo info : links) {
            //TODO Fix me
            /*
            if (fabricLoader.isModLoaded(info.getModId())) {
                //TODO check version
                try {
                    Link link = info.getLink().get();
                    link.initialize();
                    enabledLinks.add(link);
                    logger.info("Enabled link to '" + info.getName() + "'.");
                } catch (Exception e) {
                    logger.info("Failed to generateModels link '" + info.getName() + "'.");
                    e.printStackTrace();
                }
            } else {
                logger.info("Failed to link to '" + info.getName() + "', mod not found.");
            }
             */
        }
    }
}
