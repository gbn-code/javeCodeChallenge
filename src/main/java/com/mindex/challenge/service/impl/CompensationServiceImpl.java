package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation createCompensation(Compensation compensation) {
        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + compensation.getEmployee().getEmployeeId());
        }

        return compensationRepository.save(compensation);
    }

    @Override
    public Compensation getCompensation(String employeeId) {
        return compensationRepository.findByEmployee_EmployeeId(employeeId);
    }
}