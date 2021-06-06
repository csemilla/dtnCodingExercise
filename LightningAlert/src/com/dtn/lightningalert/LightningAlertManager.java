package com.dtn.lightningalert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dtn.lightningalert.dao.AssetDao;
import com.dtn.lightningalert.dao.AssetDaoImpl;
import com.dtn.lightningalert.pojo.FlashType;
import com.dtn.lightningalert.pojo.Strike;
import com.dtn.lightningalert.service.AssetService;
import com.dtn.lightningalert.service.AssetServiceImpl;
import com.dtn.lightningalert.service.LightningAlertService;
import com.dtn.lightningalert.service.LightningAlertServiceImpl;

public class LightningAlertManager {

	private String lightningJSONPath;
	private String assetsJSONPath;
	private LightningAlertService lightningAlertSvc;
	private BufferedReader bufferedReader;
	private Calendar calendar;
	
	public LightningAlertManager(String lightningJSONPath, String assetsJSONPath) {
		this.lightningJSONPath = lightningJSONPath != null ? lightningJSONPath : "";
		this.assetsJSONPath = assetsJSONPath != null ? assetsJSONPath : "";
	}
	
	public void init() {
		AssetDao assetDao = new AssetDaoImpl(assetsJSONPath);
		assetDao.init();
		
		AssetService assetSvc = new AssetServiceImpl(assetDao);
		lightningAlertSvc = new LightningAlertServiceImpl(assetSvc);
		
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(lightningJSONPath)));
		} catch (FileNotFoundException e) {
			System.out.println("Can't find " + lightningJSONPath);
			e.printStackTrace();
		}
		
		calendar = Calendar.getInstance();
	}
	
	public void run() {
		try {
			JSONParser parser = new JSONParser();
			String json;
			while (bufferedReader != null && (json = bufferedReader.readLine()) != null) {
				try {
					JSONObject jsonObject = (JSONObject)parser.parse(json);
					lightningAlertSvc.lightningAlert(createStrike(jsonObject));
				} catch (ParseException e) {
					System.out.println("Invalid JSON format." + json);
					e.printStackTrace();
				} catch (NumberFormatException | ClassCastException e) {
					System.out.println("Invalid value." + json);
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Strike createStrike(JSONObject json) {
		Strike strike = new Strike();
		
		strike.setFlashType(FlashType.getFlashType(((Long)json.get("flashType")).intValue()));
		calendar.setTimeInMillis((long)json.get("strikeTime"));
		strike.setStrikeTime(calendar.getTime());
		strike.setLatitude((double)json.get("latitude"));
		strike.setLongitude((double)json.get("longitude"));
		strike.setPeakAmps(((Long)json.get("peakAmps")).intValue());
		strike.setReserved((String)json.get("reserved"));
		strike.setIcHeight(((Long)json.get("icHeight")).intValue());
		calendar.setTimeInMillis((long)json.get("receivedTime"));
		strike.setReceivedTime(calendar.getTime());
		strike.setNumberOfSensors(((Long)json.get("numberOfSensors")).intValue());
		strike.setMultiplicity(((Long)json.get("multiplicity")).intValue());
		
		return strike;
	}
	
}
