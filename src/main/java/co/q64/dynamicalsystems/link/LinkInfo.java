package co.q64.dynamicalsystems.link;

import dagger.Lazy;

public interface LinkInfo {
	public String getModId();
	
	public String getName();
	
	public Lazy<? extends Link> getLink();
}
