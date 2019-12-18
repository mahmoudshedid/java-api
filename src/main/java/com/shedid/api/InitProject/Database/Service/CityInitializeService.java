package com.shedid.api.InitProject.Database.Service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * CityInitializeService
 */
public interface CityInitializeService
{
    void cityInitialize(String jsonString) throws JsonParseException, JsonMappingException, IOException, InterruptedException;
}