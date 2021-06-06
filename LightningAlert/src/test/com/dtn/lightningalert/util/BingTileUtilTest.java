package test.com.dtn.lightningalert.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dtn.lightningalert.service.AssetService;
import com.dtn.lightningalert.util.BingTileUtil;

public class BingTileUtilTest {
	
	@Test
	public void latLongToQuadkey() {
		int[] pixel = BingTileUtil.latLongToPixelXY(33.7884231, -96.7215206, AssetService.LEVEL_OF_DETAIL);
		int[] tile = BingTileUtil.pixelXYToTileXY(pixel[0], pixel[1]);
		String quadkey = BingTileUtil.tileXYToQuadKey(tile[0], tile[1], AssetService.LEVEL_OF_DETAIL);
		
		assertEquals("023112310233",quadkey);
	}

}
