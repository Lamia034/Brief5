package com.bank.DAO;

import com.bank.Entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOImplTest {

    @Test
    void create() {
        Employee emp = new Employee();
        emp.setFirstName("test");
        emp.setLastName("test");
        emp.setAddress("address");
        emp.setPhone("123456789");
        emp.setBirthDay(LocalDate.parse("1999-06-30"));
        emp.setDateOfRecrutment(LocalDate.parse("2023-10-20"));
        emp.setCreatedClient(new ArrayList<>());
        emp.setAgency(new AgencyDAOImpl().findByCode("agency1").get());
        Optional<Employee> employeeOptional = new EmployeeDAOImpl().create(emp);
        Assertions.assertTrue(employeeOptional.isPresent());
    }
}