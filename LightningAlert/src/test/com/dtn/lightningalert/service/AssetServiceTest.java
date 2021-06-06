package test.com.dtn.lightningalert.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dtn.lightningalert.dao.AssetDaoImpl;
import com.dtn.lightningalert.pojo.Asset;
import com.dtn.lightningalert.service.AssetService;
import com.dtn.lightningalert.service.AssetServiceImpl;

public class AssetServiceTest {

	private static AssetDaoImpl assetDao;
	private static AssetService assetSvc;

	@BeforeClass
	public static void setup() {
		assetDao = new AssetDaoImpl("assets.json");
		assetDao.init();

		AssetServiceImpl assetSvcImpl = new AssetServiceImpl();
		assetSvcImpl.setAssetDao(assetDao);
		assetSvc = assetSvcImpl;
	}
	
	@Test
	public void getAsset() {
		Asset asset = assetSvc.getAsset(33.0188648, -98.3022172);
		
		assertNotNull(asset);
	}
	
	@Test
	public void invalidCoordinates() {
		Asset asset = assetSvc.getAsset(90, 200);
		
		assertNull(asset);
	}

}
