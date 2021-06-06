package com.dtn.lightningalert.util;

public class BingTileUtil {

	private static final double EARTH_RADIUS = 6378137;
	private static final double MIN_LATITUDE = -85.05112878;
	private static final double MAX_LATITUDE = 85.05112878;
	private static final double MIN_LONGITUDE = -180;
	private static final double MAX_LONGITUDE = 180;

	private static double clip(double n, double minValue, double maxValue) {
		return Math.min(Math.max(n, minValue), maxValue);
	}

	public static int mapSize(int levelOfDetail) {
		return 256 << levelOfDetail;
	}

	public static double groundResolution(double latitude, int levelOfDetail) {
		latitude = clip(latitude, MIN_LATITUDE, MAX_LATITUDE);
		return Math.cos(latitude * Math.PI / 180) * 2 * Math.PI * EARTH_RADIUS / mapSize(levelOfDetail);
	}

	public static double mapScale(double latitude, int levelOfDetail, int screenDpi) {
		return groundResolution(latitude, levelOfDetail) * screenDpi / 0.0254;
	}

	/**
	 * @return int[] This returns int[0] for pixel x and int[1] for pixel y
	 */
	public static int[] latLongToPixelXY(double latitude, double longitude, int levelOfDetail) {
		int[] pixel = new int[2];
		latitude = clip(latitude, MIN_LATITUDE, MAX_LATITUDE);
		longitude = clip(longitude, MIN_LONGITUDE, MAX_LONGITUDE);

		double x = (longitude + 180) / 360;
		double sinLatitude = Math.sin(latitude * Math.PI / 180);
		double y = 0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);

		int mapSize = mapSize(levelOfDetail);
		pixel[0] = (int) clip(x * mapSize + 0.5, 0, mapSize - 1);
		pixel[1] = (int) clip(y * mapSize + 0.5, 0, mapSize - 1);

		return pixel;
	}

	/**
	 * @return double[] This returns double[0] for latitude and double[1] for
	 *         longitude
	 */
	public static double[] pixelXYToLatLong(int pixelX, int pixelY, int levelOfDetail) {
		double[] coordinates = new double[2];
		double mapSize = mapSize(levelOfDetail);
		double x = (clip(pixelX, 0, mapSize - 1) / mapSize) - 0.5;
		double y = 0.5 - (clip(pixelY, 0, mapSize - 1) / mapSize);

		double latitude = 90 - 360 * Math.atan(Math.exp(-y * 2 * Math.PI)) / Math.PI;
		double longitude = 360 * x;

		coordinates[0] = latitude;
		coordinates[1] = longitude;

		return coordinates;
	}

	/**
	 * @return int[] This returns int[0] for tile x and int[1] for tile y
	 */
	public static int[] pixelXYToTileXY(int pixelX, int pixelY) {
		int[] tile = new int[2];
		tile[0] = pixelX / 256;
		tile[1] = pixelY / 256;

		return tile;
	}

	/**
	 * @return int[] This returns int[0] for pixel x and int[1] for pixel y
	 */
	public static int[] tileXYToPixelXY(int tileX, int tileY) {
		int[] pixel = new int[2];
		pixel[0] = tileX * 256;
		pixel[1] = tileY * 256;

		return pixel;
	}

	public static String tileXYToQuadKey(int tileX, int tileY, int levelOfDetail)  
    {  
        StringBuilder quadKey = new StringBuilder();  
        for (int i = levelOfDetail; i > 0; i--)  
        {  
            char digit = '0';  
            int mask = 1 << (i - 1);  
            if ((tileX & mask) != 0)  
            {  
                digit++;  
            }  
            if ((tileY & mask) != 0)  
            {  
                digit++;  
                digit++;  
            }  
            quadKey.append(digit);  
        }  
        return quadKey.toString();  
    }
}
