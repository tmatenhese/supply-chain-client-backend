package com.supplychain.supplychain.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class NetworkDao {
	@Value( "${channel:channel2}" )
	private String channel;
	@Value( "${contract:ProductTransfer}" )
	private String contract;

	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	@Override
	public String toString() {
		return "NetworkDao [channel=" + channel + ", contract=" + contract + "]";
	}

}
