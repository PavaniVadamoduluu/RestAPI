package com.RestAPI.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.RestAPI.model.LocationModel;

public class SpringRestClient {

	private static final String GET_LOCATIONS_ENDPOINT_URL = "http://localhost:8090/api/location";
	private static final String GET_LOCATION_ENDPOINT_URL = "http://localhost:8090/api/location/{id}";
	private static final String CREATE_LOCATION_ENDPOINT_URL = "http://localhost:8090/api/location";
	private static final String UPDATE_LOCATION_ENDPOINT_URL = "http://localhost:8090/api/location/{id}";
	private static final String DELETE_LOCATION_ENDPOINT_URL = "http://localhost:8090/api/location/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new location
		springRestClient.createLocation();
		
		// Step 2: get new created location from step1
		springRestClient.getLocById();
		
		// Step3: get all locations
		springRestClient.getAlllocations();
		
		// Step4: Update location with id = 1
		springRestClient.updateLocation();
		
		// Step5: Delete location with id = 1
		springRestClient.deleteLoc();
	}

	///getAlllocations
	private void getAlllocations() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_LOCATION_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);
		System.out.println(result);
	}

	////getLocById
	private void getLocById() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		LocationModel result = restTemplate.getForObject(GET_LOCATIONS_ENDPOINT_URL, LocationModel.class, params);

		System.out.println(result);
	}

	///createLocation
	private void createLocation() {
		LocationModel location = new LocationModel("xyz-colony", "vizag", "AP", "India", 530017);

		RestTemplate restTemplate = new RestTemplate();
		LocationModel result = restTemplate.postForObject(CREATE_LOCATION_ENDPOINT_URL, location, LocationModel.class);

		System.out.println(result+"----createLocation");
	}

	///updateLocation
	private void updateLocation() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		LocationModel updateLocation = new LocationModel("abc-colony", "hyd", "TS","India", 12345);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_LOCATION_ENDPOINT_URL, updateLocation, params);
	}

	//deleteLoc
	private void deleteLoc() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_LOCATION_ENDPOINT_URL, params);
	}
}
