package com.shedid.api.Region.Service;

import java.util.List;

import com.shedid.api.Region.Model.State;

/**
 * StateService
 */
public interface StateService
{
    State getStateById(long id);

    List<State> getStatesByCountryId(long countryId);

    List<State> getStates();

    void save(State state);

    long count();
}