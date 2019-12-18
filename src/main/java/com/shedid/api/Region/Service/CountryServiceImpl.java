package com.shedid.api.Region.Service;

import com.shedid.api.Region.Model.Country;
import com.shedid.api.Region.Repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CountryServiceImpl
 */
@Service("countryService")
public class CountryServiceImpl implements CountryService
{
    private final CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void saveCountry(Country Country)
    {
        repository.save(Country);
    }
}