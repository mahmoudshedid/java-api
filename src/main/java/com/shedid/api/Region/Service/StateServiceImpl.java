package com.shedid.api.Region.Service;

import java.util.List;

import com.shedid.api.Region.Model.State;
import com.shedid.api.Region.Repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StateServiceImpl
 */
@Service("stateService")
public class StateServiceImpl implements StateService
{
    private StateRepository repository;

    @Autowired
    public StateServiceImpl(StateRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public State getStateById(long id)
    {
        return repository.findById(id);
    }

    @Override
    public List<State> getStatesByCountryId(long countryId)
    {
        return repository.findByCountryId(countryId);
    }

    @Override
    public List<State> getStates()
    {
        return repository.findAll();
    }

    @Override
    public void save(State state)
    {
        repository.save(state);
    }

    @Override
    public long count()
    {
        return repository.count();
    }
}