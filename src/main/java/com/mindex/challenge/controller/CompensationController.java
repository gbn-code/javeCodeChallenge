package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

/**
 * Controller for Compensation, exposes a REST API for managing employee compensation.
 */
@RestController
@RequestMapping("/compensation")
public class CompensationController {

    @Autowired
    private CompensationService compensationService;

    /**
     * POST endpoint to create a new compensation entry.
     */
    @PostMapping
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        return compensationService.createCompensation(compensation);
    }

    /**
     * GET endpoint to retrieve the compensation details for an employee.
     */
    @GetMapping("/{employeeId}")
    public Compensation getCompensation(@PathVariable String employeeId) {
        return compensationService.getCompensation(employeeId);
    }
}

