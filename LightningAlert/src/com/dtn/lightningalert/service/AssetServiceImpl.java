package com.dtn.lightningalert.service;

import com.dtn.lightningalert.dao.AssetDao;
import com.dtn.lightningalert.pojo.Asset;
import com.dtn.lightningalert.util.BingTileUtil;

public class AssetServiceImpl implements AssetService {
	
	private AssetDao assetDao;

	public AssetServiceImpl() {
		
	}
	
	public AssetServiceImpl(AssetDao assetDao) {
		this.assetDao = assetDao;
	}
	
	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}
	
	@Override
	public Asset getAsset(double latitude, double longitude) {
		int[] pixel = BingTileUtil.latLongToPixelXY(latitude, longitude, LEVEL_OF_DETAIL);
		int[] tile = BingTileUtil.pixelXYToTileXY(pixel[0], pixel[1]);
		String quadkey = BingTileUtil.tileXYToQuadKey(tile[0], tile[1], LEVEL_OF_DETAIL);
		
		return assetDao.getAsset(quadkey);
	}

}
