package com.shedid.api.Region.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.shedid.api.Region.Model.City;

/**
 * CityRepository
 */
public interface CityRepository extends JpaRepository<City, Long>
{
    City findById(long id);

    List<City> findByStateId(long stateId);
}