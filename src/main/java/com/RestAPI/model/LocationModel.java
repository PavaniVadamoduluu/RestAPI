package com.RestAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class LocationModel {
	
	
	private int id;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String country;

	private int zipcode;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public LocationModel() {
		
	}

	public LocationModel(String address, String city, String state, String country, int zipcode) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "LocationModel [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", country="
				+ country + ", zipcode=" + zipcode + "]";
	}
	
	

}
