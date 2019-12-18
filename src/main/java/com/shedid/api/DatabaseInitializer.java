package com.shedid.api;

import static java.lang.System.exit;

import java.io.IOException;
import com.shedid.api.InitProject.Database.Service.CityInitializeService;
import com.shedid.api.InitProject.Database.Service.CountryInitializeService;
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

    @Autowired 
    private CountryInitializeService countryInitializeService;

    @Autowired
    private CityInitializeService cityInitializeService;

    @Autowired
    private StateInitializeService stateInitializeService;

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(DatabaseInitializer.class, args);
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException
    {
        countryInitializeService.countryInitialize("/json/countries.json");

        stateInitializeService.stateInitialize("/json/states.json");

        cityInitializeService.cityInitialize("/json/cities.json");

        System.out.println("[+] Done!");

        exit(0);
    }
}