package com.shedid.api.Region.Service;

import java.util.List;

import com.shedid.api.Region.Model.City;
import com.shedid.api.Region.Repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CityServiceImpl
 */
@Service("cityService")
public class CityServiceImpl implements CityService
{
    private CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public City getCityById(long id)
    {
        return repository.findById(id);
    }

    @Override
    public List<City> getCitiesByStateId(long stateId)
    {
        return repository.findByStateId(stateId);
    }

    @Override
    public List<City> getCities()
    {
        return repository.findAll();
    }

    @Override
    public void save(City city)
    {
        repository.save(city);
    }

    @Override
    public long count()
    {
        return repository.count();
    }
}