package com.shedid.api.InitProject.Database.Service;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shedid.api.InitProject.Database.Model.CountryInitialize;
import com.shedid.api.InitProject.Database.Repository.CountryInitializeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
// import java.util.concurrent.TimeUnit;

import com.shedid.api.InitProject.Database.Service.CountryInitializeService;

/**
 * CountryInitializeServiceImpl
 */
@Service("countryInitializeService")
public class CountryInitializeServiceImpl implements CountryInitializeService
{
    private final CountryInitializeRepository repository;

    @Autowired
    public CountryInitializeServiceImpl(CountryInitializeRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void countryInitialize(String jsonString, long userId) throws JsonParseException, JsonMappingException, IOException, InterruptedException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonString);
        List<CountryInitialize> listCountries = mapper.readValue(inputStream, new TypeReference<List<CountryInitialize>>(){});

        CountryInitialize countryInitialize = new CountryInitialize();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (CountryInitialize country : listCountries) {
            countryInitialize.setId(country.getId());
            countryInitialize.setCreatedBy(userId);
            countryInitialize.setModifiedBy(userId);
            countryInitialize.setName(country.getName());
            countryInitialize.setSortName(country.getSortName());
            countryInitialize.setPhoneCode(country.getPhoneCode());
            countryInitialize.setCreatedAt(timestamp);
            countryInitialize.setUpdatedAt(timestamp);

            System.out.println("[+] Start Add Countries to Database.(" + countryInitialize + ")");
            repository.save(countryInitialize);
            System.out.println("[+] Success add Countries to Database.(" + countryInitialize + ")");
            // TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("[+] Complete add Countries to Database.");
        System.out.println("==========================================================================================");
    }
}