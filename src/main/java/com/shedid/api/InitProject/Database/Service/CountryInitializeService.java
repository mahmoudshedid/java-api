package com.shedid.api.InitProject.Database.Service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * CountryInitializeService
 */
public interface CountryInitializeService
{
    void countryInitialize(String jsonString, long userId) throws JsonParseException, JsonMappingException, IOException, InterruptedException;
}