package co.q64.dynamicalsystems.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.util.Logger;
import net.fabricmc.loader.api.FabricLoader;

@Singleton
public class LinkManager {
	protected @Inject Logger logger;
	protected @Inject Set<LinkInfo> links;
	protected @Inject FabricLoader fabricLoader;

	private List<Link> enabledLinks = new ArrayList<>();

	protected @Inject LinkManager() {}

	public void initializeLinks() {
		for (LinkInfo info : links) {
			if (fabricLoader.isModLoaded(info.getModId())) {
				//TODO check version
				try {
					Link link = info.getLink().get();
					link.initialize();
					enabledLinks.add(link);
					logger.info("Enabled link to '" + info.getName() + "'.");
				} catch (Exception e) {
					logger.info("Failed to load link '" + info.getName() + "'.");
					e.printStackTrace();
				}
			} else {
				logger.info("Failed to link to '" + info.getName() + "', mod not found.");
			}
		}
	}
}
