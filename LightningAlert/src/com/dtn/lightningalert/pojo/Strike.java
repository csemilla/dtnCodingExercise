package com.dtn.lightningalert.pojo;

import java.util.Date;

public class Strike {

	private FlashType flashType;
	private Date strikeTime;
	private double latitude;
	private double longitude;
	private int peakAmps;
	private String reserved;
	private int icHeight;
	private Date receivedTime;
	private int numberOfSensors;
	private int multiplicity;

	public Strike() {

	}

	public FlashType getFlashType() {
		return flashType;
	}

	public void setFlashType(FlashType flashType) {
		this.flashType = flashType;
	}

	public Date getStrikeTime() {
		return strikeTime;
	}

	public void setStrikeTime(Date strikeTime) {
		this.strikeTime = strikeTime;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getPeakAmps() {
		return peakAmps;
	}

	public void setPeakAmps(int peakAmps) {
		this.peakAmps = peakAmps;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public int getIcHeight() {
		return icHeight;
	}

	public void setIcHeight(int icHeight) {
		this.icHeight = icHeight;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public int getNumberOfSensors() {
		return numberOfSensors;
	}

	public void setNumberOfSensors(int numberOfSensors) {
		this.numberOfSensors = numberOfSensors;
	}

	public int getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}

}
