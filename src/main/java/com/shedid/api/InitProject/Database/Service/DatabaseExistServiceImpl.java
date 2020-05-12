package com.shedid.api.InitProject.Database.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shedid.api.InitProject.Database.Model.CityInitialize;
import com.shedid.api.InitProject.Database.Model.CountryInitialize;
import com.shedid.api.InitProject.Database.Model.StateInitialize;
import com.shedid.api.Region.Service.CityService;
import com.shedid.api.Region.Service.CountryService;
import com.shedid.api.Region.Service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * DatabaseExistImpl
 */
@Service("databaseExistService")
public class DatabaseExistServiceImpl implements DatabaseExistService
{
    private final CountryService countryService;
    private final StateService stateService;
    private final CityService cityService;

    @Autowired
    public DatabaseExistServiceImpl(CountryService countryService, StateService stateService, CityService cityService)
    {
        this.countryService = countryService;
        this.stateService = stateService;
        this.cityService = cityService;
    }

    @Override
    public boolean checkTableExist(String tableName)
    {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:HY_FLAT");

            DatabaseMetaData dbm = con.getMetaData();
            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean countryDataExist(String fileUrl) throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = TypeReference.class.getResourceAsStream(fileUrl);
        List<CountryInitialize> listCountries = mapper.readValue(inputStream, new TypeReference<List<CountryInitialize>>() {});

        long countCountry = countryService.count();

        return listCountries.size() == countCountry;
    }

    @Override
    public boolean stateDataExist(String fileUrl) throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = TypeReference.class.getResourceAsStream(fileUrl);
        List<StateInitialize> listStates = mapper.readValue(inputStream, new TypeReference<List<StateInitialize>>() {});

        return listStates.size() == stateService.count();
    }

    @Override
    public boolean cityDataExist(String fileUrl) throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = TypeReference.class.getResourceAsStream(fileUrl);
        List<CityInitialize> listCities = mapper.readValue(inputStream, new TypeReference<List<CityInitialize>>() {});

        return listCities.size() == cityService.count();
    }
}
