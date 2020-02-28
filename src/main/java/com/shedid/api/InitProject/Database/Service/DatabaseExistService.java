package com.shedid.api.InitProject.Database.Service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * DatabaseExist
 */
public interface DatabaseExistService
{
    boolean checkTableExist(String tableName);

    boolean countryDataExist(String fileUrl) throws JsonParseException, JsonMappingException, IOException;

    boolean stateDataExist(String fileUrl) throws JsonParseException, JsonMappingException, IOException;

    boolean cityDataExist(String fileUrl) throws JsonParseException, JsonMappingException, IOException;
}