package com.shedid.api;

import java.io.IOException;
import com.shedid.api.InitProject.Database.Service.CityInitializeService;
import com.shedid.api.InitProject.Database.Service.CountryInitializeService;
import com.shedid.api.InitProject.Database.Service.DatabaseExistService;
import com.shedid.api.InitProject.Database.Service.StateInitializeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DatabaseInitializer
 */
@SpringBootApplication
public class DatabaseInitializer implements CommandLineRunner
{
    private CountryInitializeService countryInitializeService;

    private CityInitializeService cityInitializeService;

    private StateInitializeService stateInitializeService;

    private DatabaseExistService databaseExistService;

    @Autowired
    public DatabaseInitializer(CountryInitializeService countryInitializeService, CityInitializeService cityInitializeService, StateInitializeService stateInitializeService, DatabaseExistService databaseExistService)
    {
        this.countryInitializeService = countryInitializeService;
        this.cityInitializeService = cityInitializeService;
        this.stateInitializeService = stateInitializeService;
        this.databaseExistService = databaseExistService;
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(DatabaseInitializer.class, args);
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException
    {
        if (!databaseExistService.countryDataExist("/json/countries.json")) countryInitializeService.countryInitialize("/json/countries.json");

        if (!databaseExistService.stateDataExist("/json/states.json")) stateInitializeService.stateInitialize("/json/states.json");

        if (!databaseExistService.cityDataExist("/json/cities.json")) cityInitializeService.cityInitialize("/json/cities.json");

        System.out.println("[+] Initializing database, Done!");

    }
}