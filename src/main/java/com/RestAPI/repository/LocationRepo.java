package com.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RestAPI.model.LocationModel;

@Repository
public interface LocationRepo extends JpaRepository<LocationModel, Long>{

}
