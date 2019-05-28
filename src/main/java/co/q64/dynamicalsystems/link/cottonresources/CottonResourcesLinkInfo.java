package co.q64.dynamicalsystems.link.cottonresources;

import javax.inject.Inject;

import co.q64.dynamicalsystems.link.LinkInfo;
import dagger.Lazy;
import lombok.Getter;

@Getter
public class CottonResourcesLinkInfo implements LinkInfo {
	private final String modId = "cotton-resources";
	private final String name = "Cotton Resources";
	
	protected @Inject Lazy<CottonResourcesLink> link;
	
	protected @Inject CottonResourcesLinkInfo() {}
}
