package com.RestAPI.RestAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.RestAPI.model.LocationModel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationContext.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/location",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testgetLocById() {
		LocationModel loc = restTemplate.getForObject(getRootUrl() + "/location/1", LocationModel.class);
		System.out.println(loc.getAddress());
		assertNotNull(loc);
	}

	@Test
	public void testcreateLocation() {
		LocationModel loc = new LocationModel();
		loc.setAddress("xyz-colony");
		loc.setCity("vizag");
		loc.setState("AP");
		loc.setCountry("India");
		loc.setZipcode(530017);

		ResponseEntity<LocationModel> postResponse = restTemplate.postForEntity(getRootUrl() + "/location", loc, LocationModel.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testupdateLocation() {
		int id = 1;
		LocationModel loc = restTemplate.getForObject(getRootUrl() + "/location/" + id,  LocationModel.class);
		loc.setAddress("abc-colony");
		loc.setCity("Hyd");
		loc.setState("TS");
		loc.setCountry("India");
		loc.setZipcode(12345);

		restTemplate.put(getRootUrl() + "/location/" + id, loc);

		LocationModel updateLocation = restTemplate.getForObject(getRootUrl() + "/location/" + id, LocationModel.class);
		assertNotNull(updateLocation);
	}

	@Test
	public void testdeleteLoc() {
		int id = 2;
		LocationModel loc = restTemplate.getForObject(getRootUrl() + "/location/" + id, LocationModel.class);
		assertNotNull(loc);

		restTemplate.delete(getRootUrl() + "/location/" + id);

		try {
			loc = restTemplate.getForObject(getRootUrl() + "/location/" + id, LocationModel.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}