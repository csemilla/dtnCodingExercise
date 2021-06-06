package test.com.dtn.lightningalert;

import org.junit.Test;

import com.dtn.lightningalert.LightningAlertManager;

public class LightningAlertManagerTest {

	@Test
	public void run() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("lightning.json", "assets.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void lightningJsonNotFound() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("notfound.json", "assets.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void assetsJsonNotFound() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("lightning.json", "notfound.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void invalidLigthningJson() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("invalid.json", "assets.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void invalidDataLigthningJson() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("invaliddata.json", "assets.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void nullFile() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager(null, "assets.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void emptyFile() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("", "assets.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}
	
	@Test
	public void swapFiles() {
		LightningAlertManager lightningAlertMgr = new LightningAlertManager("assets.json", "lightning.json");
		lightningAlertMgr.init();
		lightningAlertMgr.run();
	}

}
