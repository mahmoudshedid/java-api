package com.shedid.api.Region.Repository;

import com.shedid.api.Region.Model.Country;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CountryRepository
 */
public interface CountryRepository extends JpaRepository<Country, Long>
{

}