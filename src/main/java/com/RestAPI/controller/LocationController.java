package com.RestAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RestAPI.exception.ResourceNotFoundException;
import com.RestAPI.model.LocationModel;
import com.RestAPI.repository.LocationRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocationController {

	@Autowired
	LocationRepo LocRepo;
	
	//GET METHOD
	@GetMapping("/location")
	public List<LocationModel> getAlllocations() {
		return LocRepo.findAll();
	}

	//GET BY ID
	@GetMapping("/location/{id}")
	public ResponseEntity<LocationModel> getLocById(@PathVariable(value = "id") Long LocId)
			throws ResourceNotFoundException {
		LocationModel location = LocRepo.findById(LocId)
				.orElseThrow(() -> new ResourceNotFoundException("Location is not found for this id :: " + LocId));
		return ResponseEntity.ok().body(location);
	}

	//POST METHOD
	@PostMapping("/location")
	public LocationModel createLocation(@Validated @RequestBody LocationModel location) {
		return LocRepo.save(location);
	}

	//PUT METHOD
	@PutMapping("/location/{id}")
	public ResponseEntity<LocationModel> updateLocation(@PathVariable(value = "id") Long LocId,
			@Validated @RequestBody LocationModel LocDetails) throws ResourceNotFoundException {
		LocationModel location = LocRepo.findById(LocId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + LocId));

		location.setAddress(LocDetails.getAddress());
		location.setCity(LocDetails.getCity());
		location.setState(LocDetails.getState());
		location.setCountry(LocDetails.getCountry());
		location.setZipcode(LocDetails.getZipcode());
		
		final LocationModel updatedLocation = LocRepo.save(location);
		return ResponseEntity.ok(updatedLocation);
	}

	//DELETE METHOD
	@DeleteMapping("/location/{id}")
	public Map<String, Boolean> deleteLoc(@PathVariable(value = "id") Long LocId)
			throws ResourceNotFoundException {
		LocationModel location = LocRepo.findById(LocId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + LocId));

		LocRepo.delete(location);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

