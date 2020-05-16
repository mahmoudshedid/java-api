package com.shedid.api.Gender.Service;

import com.shedid.api.Gender.Model.Gender;

import java.util.List;

public interface GenderService {

    Gender findGenderById(long genderId);

    Gender findGenderByType(String type);

    List<Gender> findAllGender();

    boolean isGenderExist(String type);

    Gender createGender(Gender gender);

    Gender createGenderByType(String type);
}