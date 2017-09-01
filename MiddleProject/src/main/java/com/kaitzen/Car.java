package com.kaitzen;

import java.util.List;

public class Car {
	private String domain;
	private Integer doors;
	private String color;
	private String fuel;
	private String kilometers;
	private Double baggageSpace;
	private Integer passengersSpace;
	
	Car() {}
	
	Car(String domain, Integer doors, String color, String fuel, String kilometers,	Double baggageSpace, Integer passengersSpace) {
		this.domain = domain;
		this.doors = doors;
		this.color = color;
		this.fuel = fuel;
		this.kilometers = kilometers;
		this.baggageSpace = baggageSpace;
		this.passengersSpace = passengersSpace;
	}
	
	public Car save() {
		return this;
	}
	
	public Car load(String domain) {
		return this;
	}
	
	public Car update() {
		return this;
	}
	
	public List<Car> search(String attribute, String value) {
		 return null;
	 }
}
