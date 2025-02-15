package com.mindex.challenge.service.impl;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
public class ReportingStructureServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private ReportingStructureServiceImpl reportingStructureService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReportingStructure() {
        // Create an employee with two direct reports.
        Employee employee = new Employee();
        employee.setEmployeeId("1");

        Employee directReport1 = new Employee();
        directReport1.setEmployeeId("2");

        Employee directReport2 = new Employee();
        directReport2.setEmployeeId("3");

        employee.setDirectReports(Arrays.asList(directReport1, directReport2));

        // Stub the repository methods to return our test employees.
        when(employeeRepository.findByEmployeeId("1")).thenReturn(employee);
        when(employeeRepository.findByEmployeeId("2")).thenReturn(new Employee());
        when(employeeRepository.findByEmployeeId("3")).thenReturn(new Employee());

        ReportingStructure reportingStructure = reportingStructureService.getReportingStructure("1");
        assertEquals(2, reportingStructure.getNumberOfReports());
    }
}
