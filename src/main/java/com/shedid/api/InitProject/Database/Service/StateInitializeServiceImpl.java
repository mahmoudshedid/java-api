package com.shedid.api.InitProject.Database.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
// import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shedid.api.InitProject.Database.Model.StateInitialize;
import com.shedid.api.InitProject.Database.Repository.StateInitializeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StateInitializeServiceImpl
 */
@Service("stateInitializeService")
public class StateInitializeServiceImpl implements StateInitializeService
{
    private StateInitializeRepository repository;

    @Autowired
    public StateInitializeServiceImpl(StateInitializeRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void stateInitialize(String jsonString) throws JsonParseException, JsonMappingException, IOException, InterruptedException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonString);
        List<StateInitialize> listStates = mapper.readValue(inputStream, new TypeReference<List<StateInitialize>>(){});

        StateInitialize stateInitialize = new StateInitialize();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (StateInitialize state : listStates) {
            stateInitialize.setId(state.getId());
            stateInitialize.setName(state.getName());
            stateInitialize.setCountryId(state.getCountryId());
            stateInitialize.setCreatedAt(timestamp);
            stateInitialize.setUpdatedAt(timestamp);

            System.out.println("[+] Start Add States to Database.(" + stateInitialize + ")");
            repository.save(stateInitialize);
            System.out.println("[+] Success add States to Database.(" + stateInitialize + ")");
            // TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("[+] Complete add States to Database.");
        System.out.println("==========================================================================================");
    }
}