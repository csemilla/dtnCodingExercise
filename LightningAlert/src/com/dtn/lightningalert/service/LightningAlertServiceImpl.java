package com.dtn.lightningalert.service;

import java.util.HashSet;
import java.util.Set;

import com.dtn.lightningalert.pojo.Asset;
import com.dtn.lightningalert.pojo.FlashType;
import com.dtn.lightningalert.pojo.Strike;

public class LightningAlertServiceImpl implements LightningAlertService {

	private Set<Asset> lightningAlerts;
	private AssetService assetSvc;

	public LightningAlertServiceImpl() {
		lightningAlerts = new HashSet<Asset>();
	}
	
	public LightningAlertServiceImpl(AssetService assetSvc) {
		lightningAlerts = new HashSet<Asset>();
		this.assetSvc = assetSvc;
	}

	public AssetService getAssetSvc() {
		return assetSvc;
	}

	public void setAssetSvc(AssetService assetSvc) {
		this.assetSvc = assetSvc;
	}

	@Override
	public void lightningAlert(Strike strike) {
		if (strike != null && strike.getFlashType() != FlashType.Heartbeat) {
			Asset asset = assetSvc.getAsset(strike.getLatitude(), strike.getLongitude());
			if (asset != null && lightningAlerts.add(asset))
				System.out.println("lightning alert for " + asset.getOwner() + ":" + asset.getName());
		}
	}

}
