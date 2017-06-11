package org.edf.hifox.core.register.channel.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author WangYang
 *
 */
public class ChannelCfg {
	private List<ChannelDef> channels = new ArrayList<ChannelDef>();

	public List<ChannelDef> getChannels() {
		return channels;
	}

	public void addChannel(ChannelDef channel) {
		channels.add(channel);
	}
	

}
