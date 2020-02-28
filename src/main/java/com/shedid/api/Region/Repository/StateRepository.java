package com.shedid.api.Region.Repository;

import java.util.List;

import com.shedid.api.Region.Model.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * StateRepository
 */
public interface StateRepository extends JpaRepository<State, Long>
{
    State findById(long id);

    @Query("SELECT s FROM State s JOIN s.country c ON c.id = ?1")
    List<State> findByCountryId(long countryId);
}