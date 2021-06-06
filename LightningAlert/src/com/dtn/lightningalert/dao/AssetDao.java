package com.dtn.lightningalert.dao;

import com.dtn.lightningalert.pojo.Asset;

public interface AssetDao {

	public void init();
	public Asset getAsset(String quadKey);
	
}
