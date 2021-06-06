package test.com.dtn.lightningalert.service;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dtn.lightningalert.dao.AssetDao;
import com.dtn.lightningalert.dao.AssetDaoImpl;
import com.dtn.lightningalert.pojo.FlashType;
import com.dtn.lightningalert.pojo.Strike;
import com.dtn.lightningalert.service.AssetService;
import com.dtn.lightningalert.service.AssetServiceImpl;
import com.dtn.lightningalert.service.LightningAlertService;
import com.dtn.lightningalert.service.LightningAlertServiceImpl;

public class LightningAlertServiceTest {

	private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static PrintStream originalOut = System.out;
	private static Calendar calendar;
	private static LightningAlertService lightningAlertSvc;
	private static Strike strike;
	private static AssetService assetSvc;

	@BeforeClass
	public static void setup() {
		AssetDao assetDao = new AssetDaoImpl("assets.json");
		assetDao.init();
		
		assetSvc = new AssetServiceImpl(assetDao);
		
		calendar = Calendar.getInstance();
	    System.setOut(new PrintStream(outContent));
	    
		strike = new Strike();
		strike.setFlashType(FlashType.CloudToCloud);
		calendar.setTimeInMillis(1446761133445l);
		strike.setStrikeTime(calendar.getTime());
		strike.setLatitude(33.0188648);
		strike.setLongitude(-98.3022172);
		strike.setPeakAmps(2291);
		strike.setReserved("000");
		strike.setIcHeight(19622);
		calendar.setTimeInMillis(1446761145726l);
		strike.setReceivedTime(calendar.getTime());
		strike.setNumberOfSensors(10);
		strike.setMultiplicity(6);
	}
	
	@Before
	public void reset() {
		outContent.reset();
		lightningAlertSvc = new LightningAlertServiceImpl(assetSvc);
	}
	
	@Test
	public void lightningAlertFlashType1() {
		strike.setFlashType(FlashType.CloudToCloud);
		lightningAlertSvc.lightningAlert(strike);
		
		assertEquals("lightning alert for 132:Raynor Avenue\r\n", outContent.toString());
	}
	
	@Test
	public void lightningAlertFlashType0() {
		strike.setFlashType(FlashType.CloudToGround);
		lightningAlertSvc.lightningAlert(strike);
		
		assertEquals("lightning alert for 132:Raynor Avenue\r\n", outContent.toString());
	}
	
	@Test
	public void lightningAlertSameLocation() {
		strike.setFlashType(FlashType.CloudToCloud);
		lightningAlertSvc.lightningAlert(strike);
		
		assertEquals("lightning alert for 132:Raynor Avenue\r\n", outContent.toString());
		
		lightningAlertSvc.lightningAlert(strike);
		
		assertEquals("lightning alert for 132:Raynor Avenue\r\n", outContent.toString());
	}
	
	@Test
	public void lightningAlertFlashType9() {
		strike.setFlashType(FlashType.Heartbeat);
		lightningAlertSvc.lightningAlert(strike);
		
		assertEquals("", outContent.toString());
	}
	
	@Test
	public void nullValue() {
		lightningAlertSvc.lightningAlert(null);
		
		assertEquals("", outContent.toString());
	}
	
	@AfterClass
	public static void restoreStreams() {
	    System.setOut(originalOut);
	}

}
