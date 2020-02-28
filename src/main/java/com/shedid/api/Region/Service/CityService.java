package com.shedid.api.Region.Service;

import java.util.List;

import com.shedid.api.Region.Model.City;

/**
 * CityService
 */
public interface CityService
{
    City getCityById(long id);
    
    List<City> getCitiesByStateId(long stateId);

    List<City> getCities();

    void save(City city);

    long count();
}