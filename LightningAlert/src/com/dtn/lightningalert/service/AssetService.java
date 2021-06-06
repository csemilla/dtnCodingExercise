package com.dtn.lightningalert.service;

import com.dtn.lightningalert.pojo.Asset;

public interface AssetService {
	
	public static final int LEVEL_OF_DETAIL = 12; //assuming level of details is fixed at 12

	public Asset getAsset(double latitude, double longitude);
	
}
