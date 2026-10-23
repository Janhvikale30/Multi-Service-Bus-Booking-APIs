package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bus {
	@Id
	private Integer busId;
	private String busNo;
	private String boarding;
	private String destination;

	public Bus() {
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public void setBoarding(String boarding) {
		this.boarding = boarding;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getBusId() {
		return busId;
	}

	public String getBusNo() {
		return busNo;
	}

	public String getBoarding() {
		return boarding;
	}

	public String getDestination() {
		return destination;
	}

}
