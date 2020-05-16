package com.shedid.api.Configuration;

import com.shedid.api.Gender.Model.Gender;
import com.shedid.api.Gender.Service.GenderService;
import com.shedid.api.InitProject.Database.Service.CityInitializeService;
import com.shedid.api.InitProject.Database.Service.CountryInitializeService;
import com.shedid.api.InitProject.Database.Service.DatabaseExistService;
import com.shedid.api.InitProject.Database.Service.StateInitializeService;
import com.shedid.api.Role.Model.Role;
import com.shedid.api.Role.Service.RoleService;
import com.shedid.api.User.Model.User;
import com.shedid.api.User.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class AppInitializer {

    private final CountryInitializeService countryInitializeService;
    private final CityInitializeService cityInitializeService;
    private final StateInitializeService stateInitializeService;
    private final DatabaseExistService databaseExistService;
    private final RoleService roleService;
    private final GenderService genderService;
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    @Autowired
    public AppInitializer(
            CountryInitializeService countryInitializeService,
            CityInitializeService cityInitializeService,
            StateInitializeService stateInitializeService,
            DatabaseExistService databaseExistService,
            RoleService roleService,
            GenderService genderService,
            UserService userService
    ) {
        this.countryInitializeService = countryInitializeService;
        this.cityInitializeService = cityInitializeService;
        this.stateInitializeService = stateInitializeService;
        this.databaseExistService = databaseExistService;
        this.roleService = roleService;
        this.genderService = genderService;
        this.userService = userService;
    }

    @PostConstruct
    private void init() throws IOException, InterruptedException {
        log.info("[+] AppInitializer initialization database ...");

        Role role = new Role();
        if (!this.roleService.isRoleExist("SUPER_ADMIN")) role = this.roleService.createRoleByRole("SUPER_ADMIN");
        else role = this.roleService.findRoleByRole("SUPER_ADMIN");

        Gender gender = new Gender();
        if (!this.genderService.isGenderExist("Male")) gender = this.genderService.createGenderByType("Male");
        else gender = genderService.findGenderByType("Male");

        User user = new User();
        if (!this.userService.exitsByUsername("admin")) user = this.userService.createUserByUsername("admin", role, gender);
        else user = this.userService.findUserByUsername("admin");

        if (!databaseExistService.countryDataExist("/json/countries.json"))
            countryInitializeService.countryInitialize("/json/countries.json", user.getId());

        if (!databaseExistService.stateDataExist("/json/states.json"))
            stateInitializeService.stateInitialize("/json/states.json", user.getId());

        if (!databaseExistService.cityDataExist("/json/cities.json"))
            cityInitializeService.cityInitialize("/json/cities.json", user.getId());

        log.info("[+] Initializing database, Done!");
    }
}
