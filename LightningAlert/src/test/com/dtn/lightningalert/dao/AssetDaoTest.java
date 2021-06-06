package test.com.dtn.lightningalert.dao;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dtn.lightningalert.dao.AssetDaoImpl;
import com.dtn.lightningalert.pojo.Asset;

public class AssetDaoTest {

	private static AssetDaoImpl assetDao;
	
	@BeforeClass
	public static void setup() {
		assetDao = new AssetDaoImpl("assets.json");
	}
	
	@Test
	public void init() {
		assetDao.init();
		
		assertTrue(assetDao.getFilePath() != null);
		assertTrue(assetDao.getAssetMap().size() > 0);
	}
	
	@Test
	public void getAsset() {
		assetDao.init();
		
		Asset asset = assetDao.getAsset("023112133002");
		
		assertNotNull(asset);
		assertEquals("Mayer Park", asset.getName());
		assertEquals("023112133002", asset.getQuadkey());
		assertEquals("02115", asset.getOwner());
	}
	
	@Test
	public void fileNotFound() {
		AssetDaoImpl assetDao = new AssetDaoImpl("notfound.json");
		assetDao.init();
		
		Asset asset = assetDao.getAsset("023112133002");
		
		assertNull(asset);
	}
	
	@Test
	public void invalidJSON() {
		AssetDaoImpl assetDao = new AssetDaoImpl("invalid.json");
		assetDao.init();
		
		Asset asset = assetDao.getAsset("023112133002");
		
		assertNull(asset);
	}
	
	@Test
	public void nullFile() {
		AssetDaoImpl assetDao = new AssetDaoImpl(null);
		assetDao.init();
		
		Asset asset = assetDao.getAsset("023112133002");
		
		assertNull(asset);
	}
	
	@Test
	public void emptyFile() {
		AssetDaoImpl assetDao = new AssetDaoImpl("");
		assetDao.init();
		
		Asset asset = assetDao.getAsset("023112133002");
		
		assertNull(asset);
	}
}
