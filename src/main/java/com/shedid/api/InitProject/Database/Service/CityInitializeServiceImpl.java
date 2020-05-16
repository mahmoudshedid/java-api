package com.shedid.api.InitProject.Database.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shedid.api.InitProject.Database.Model.CityInitialize;
import com.shedid.api.InitProject.Database.Repository.CityInitializeRepository;

import com.shedid.api.Region.Model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

// import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;

/**
 * CityInitializeServiceImpl
 */
@Service("cityInitializeService")
public class CityInitializeServiceImpl implements CityInitializeService
{
    private final CityInitializeRepository repository;

    @Autowired
    public CityInitializeServiceImpl(CityInitializeRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void cityInitialize(String jsonString, long userId) throws JsonParseException, JsonMappingException, IOException, InterruptedException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonString);
        List<CityInitialize> listCities = mapper.readValue(inputStream, new TypeReference<List<CityInitialize>>() {});

        CityInitialize cityInitialize = new CityInitialize();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (CityInitialize city : listCities) {
            cityInitialize.setId(city.getId());
            cityInitialize.setName(city.getName());
            cityInitialize.setStateId(city.getStateId());
            cityInitialize.setCreatedBy(userId);
            cityInitialize.setModifiedBy(userId);
            cityInitialize.setCreatedAt(timestamp);
            cityInitialize.setUpdatedAt(timestamp);

            System.out.println("[+] Start Add City to Database.(" + cityInitialize + ")");
            repository.save(cityInitialize);
            System.out.println("[+] Success add City to Database.(" + cityInitialize + ")");
            // TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("[+] Complete add Cities to Database.");
        System.out.println("==========================================================================================");
    }
}