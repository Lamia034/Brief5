package com.bank.Service;


import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.Client;
import com.bank.Entity.Credit;
import com.bank.Entity.Employee;
import com.bank.Enum.CreditStatus;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.CreditService;
import com.bank.Service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ApplicationScoped
class CreditDAOImplTest {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCredit() throws Exception {
        Credit credit = new Credit();
        Agency agency = new Agency();
        agency.setCode("agency1");
        Client client = new Client();
        client.setCode(5);
        Employee employee = new Employee();
        employee.setRegistrationNbr(1);

        credit.setValue(555);
        credit.setStatus(CreditStatus.PENDING);
        credit.setDuration(25);
        credit.setRemark("Remark");

        credit.setAgency(agency);
        credit.setClient(client);
        credit.setEmployee(employee);
        credit.setModification_date(LocalDate.parse("2020-02-20"));
        credit.setModification_time(LocalTime.parse("11:11:11"));

        when(entityManager.persist(any(Credit.class))).thenReturn(credit);

        Optional<Credit> result = creditService.addCredit(credit);

        assertEquals(credit, result.orElse(null));
    }

    @Test
    void updateCredit() {
        Credit credit = new Credit();
        credit.setStatus(CreditStatus.PENDING);
        CreditStatus newStatus = CreditStatus.REFUSED;
        when(entityManager.find(Credit.class, 30)).thenReturn(credit);

        Optional<Credit> result = creditService.updateStatus(30, newStatus);

        assertTrue(result.isPresent());
        assertEquals(newStatus, result.get().getStatus());
    }
}

