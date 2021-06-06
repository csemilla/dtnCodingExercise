package com.dtn.lightningalert.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dtn.lightningalert.pojo.Asset;

public class AssetDaoImpl implements AssetDao {

	private String filePath;
	private Map<String, Asset> assetMap;
	
	public AssetDaoImpl(String filePath) {
		this.filePath = filePath != null ? filePath : "";
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Map<String, Asset> getAssetMap() {
		return assetMap;
	}

	public void setAssetMap(Map<String, Asset> assetMap) {
		this.assetMap = assetMap;
	}

	@Override
	public void init() {
		assetMap = new HashMap<String, Asset>();
		
		try {
			JSONParser parser = new JSONParser();
			FileReader fileReader = new FileReader(filePath);
			JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
			Iterator jsonIterator = jsonArray.iterator();
			while (jsonIterator.hasNext()) {
				Map jsonMap = (Map)jsonIterator.next();
				
				Asset asset = new Asset();
				asset.setName((String)jsonMap.get("assetName"));
				asset.setQuadkey((String)jsonMap.get("quadKey"));
				asset.setOwner((String)jsonMap.get("assetOwner"));
				
				assetMap.put(asset.getQuadkey(), asset);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Can't find " + filePath);
			System.out.println("AssetDao not initilized");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't read " + filePath);
			System.out.println("AssetDao not initilized");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Invalid JSON string.");
			System.out.println("AssetDao not initilized");
			e.printStackTrace();
		}
	}
	
	

	@Override
	public Asset getAsset(String quadKey) {
		return assetMap.get(quadKey);
	}
	
}
