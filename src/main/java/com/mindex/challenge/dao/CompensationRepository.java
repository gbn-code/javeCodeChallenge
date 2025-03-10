package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompensationRepository extends MongoRepository<Compensation, String> {

    Compensation findByEmployee_EmployeeId(String employeeId);

}