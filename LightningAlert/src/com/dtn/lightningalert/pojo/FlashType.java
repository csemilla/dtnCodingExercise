package com.dtn.lightningalert.pojo;

public enum FlashType {
	
	CloudToGround(0), CloudToCloud(1), Heartbeat(9);
	
	private final int value;
	
	private FlashType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static FlashType getFlashType(int value) {
		switch (value) {
			case 0: return CloudToCloud;
			case 1: return CloudToGround;
			case 9: return Heartbeat;
			default: return null;
		}
	}
}
