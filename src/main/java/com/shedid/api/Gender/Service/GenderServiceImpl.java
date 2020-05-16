package com.shedid.api.Gender.Service;

import com.shedid.api.Gender.Model.Gender;
import com.shedid.api.Gender.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("genderService")
public class GenderServiceImpl implements GenderService {

    private final GenderRepository repository;

    @Autowired
    public GenderServiceImpl(GenderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Gender findGenderById(long genderId) {
        return this.repository.findById(genderId);
    }

    @Override
    public Gender findGenderByType(String type) {
        return this.repository.findByType(type);
    }

    @Override
    public List<Gender> findAllGender() {
        return this.repository.findAll();
    }

    @Override
    public boolean isGenderExist(String type) {
        return this.repository.findByType(type) != null;
    }

    @Override
    public Gender createGender(Gender gender) {
        return this.repository.save(gender);
    }

    @Override
    public Gender createGenderByType(String type) {
        Gender gender = new Gender();
        gender.setType(type);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        gender.setCreatedAt(timestamp);
        gender.setUpdatedAt(timestamp);
        return this.repository.save(gender);
    }
}
