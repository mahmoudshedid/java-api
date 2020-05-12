package com.shedid.api.Configuration;

import com.shedid.api.InitProject.Database.Service.CityInitializeService;
import com.shedid.api.InitProject.Database.Service.CountryInitializeService;
import com.shedid.api.InitProject.Database.Service.DatabaseExistService;
import com.shedid.api.InitProject.Database.Service.StateInitializeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class AppInitializer {

    private final CountryInitializeService countryInitializeService;
    private final CityInitializeService cityInitializeService;
    private final StateInitializeService stateInitializeService;
    private final DatabaseExistService databaseExistService;

    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    public AppInitializer(CountryInitializeService countryInitializeService, CityInitializeService cityInitializeService, StateInitializeService stateInitializeService, DatabaseExistService databaseExistService) {
        this.countryInitializeService = countryInitializeService;
        this.cityInitializeService = cityInitializeService;
        this.stateInitializeService = stateInitializeService;
        this.databaseExistService = databaseExistService;
    }

    @PostConstruct
    private void init()  throws IOException, InterruptedException {
        log.info("[+] AppInitializer initialization database ...");
        if (!databaseExistService.countryDataExist("/json/countries.json")) countryInitializeService.countryInitialize("/json/countries.json");

        if (!databaseExistService.stateDataExist("/json/states.json")) stateInitializeService.stateInitialize("/json/states.json");

        if (!databaseExistService.cityDataExist("/json/cities.json")) cityInitializeService.cityInitialize("/json/cities.json");

        log.info("[+] Initializing database, Done!");
    }
}
