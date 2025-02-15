package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Mock
    private CompensationRepository compensationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CompensationServiceImpl compensationService;

    private Employee employee;
    private Compensation compensation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setEmployeeId("1");
        employee.setFirstName("John");
        employee.setLastName("Doe");

        compensation = new Compensation(employee, 75000, LocalDate.of(2024, 2, 13));
    }

    @Test
    public void testCreateCompensation() {
        when(employeeRepository.findByEmployeeId("1")).thenReturn(employee);
        when(compensationRepository.save(compensation)).thenReturn(compensation);

        Compensation createdCompensation = compensationService.createCompensation(compensation);
        assertNotNull(createdCompensation);
        // When comparing doubles, you need to specify a delta value
        assertEquals(75000, createdCompensation.getSalary(), 0.0);
        assertEquals(LocalDate.of(2024, 2, 13), createdCompensation.getEffectiveDate());
    }

    @Test
    public void testGetCompensation() {
        when(compensationRepository.findByEmployee_EmployeeId("1")).thenReturn(compensation);

        Compensation fetchedCompensation = compensationService.getCompensation("1");
        assertNotNull(fetchedCompensation);
        assertEquals(75000, fetchedCompensation.getSalary(), 0.0);
        assertEquals(LocalDate.of(2024, 2, 13), fetchedCompensation.getEffectiveDate());
    }
}
