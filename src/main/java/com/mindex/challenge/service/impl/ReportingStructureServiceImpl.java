package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure getReportingStructure(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee not found: " + employeeId);
        }

        int numberOfReports = countReports(employee);
        return new ReportingStructure(employee, numberOfReports);
    }


    private int countReports(Employee employee) {
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return 0;
        }

        int count = 0;
        for (Employee directReport : employee.getDirectReports()) {
            Employee fullEmployee = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
            count += 1 + countReports(fullEmployee);
        }
        return count;
    }
}