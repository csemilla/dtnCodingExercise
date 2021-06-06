package com.dtn.lightningalert;

public class LightningAlertLauncher {

	/**
	 * 
	 * @param args parameter 1 JSON lightning file; parameter 2 JSON asset file
	 */
	public static void main(String[] args) {
		LightningAlertManager manager = new LightningAlertManager(args[0], args[1]);
		manager.init();
		manager.run();
	}

}
