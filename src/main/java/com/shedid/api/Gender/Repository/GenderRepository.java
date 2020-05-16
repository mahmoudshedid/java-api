package com.shedid.api.Gender.Repository;

import com.shedid.api.Gender.Model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenderRepository extends JpaRepository<Gender, Long> {

    Gender findById(long id);

    Gender findByType(String type);
}
