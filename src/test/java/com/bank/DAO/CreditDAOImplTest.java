package com.bank.DAO;

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
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationScoped
class CreditDAOImplTest {


    private CreditDAOImpl creditDAOImpl;

    @BeforeEach
    public void setUp() {
        creditDAOImpl = new CreditDAOImpl();
      //  creditService = new CreditService(creditDAOImpl);

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
//        555, CreditStatus.valueOf("PENDING"),24,"REMARK",2,1,"agency1",1,"22-06-2009","11:12:08"

        credit.setValue(555);
        credit.setStatus(CreditStatus.valueOf("PENDING"));
        credit.setDuration(25);
        credit.setRemark("Remark");

        credit.setAgency(agency);
        credit.setClient(client);
        credit.setEmployee(employee);
        credit.setModification_date(LocalDate.parse("2020-02-20"));
        credit.setModification_time(LocalTime.parse("11:11:11"));
        Optional<Credit> result = creditDAOImpl.create(credit);

        System.out.println(result.get().getValue());
        assertEquals(credit, result.get());
    }


    @Test
    void updateCredit() {
        Credit credit = new Credit();
        credit.setStatus(CreditStatus.PENDING);
        CreditStatus newStatus = CreditStatus.REFUSED;
        Optional<Credit> result = creditDAOImpl.updateStatus(30, newStatus);
        assertTrue(result.isPresent());
        assertEquals(newStatus, result.get().getStatus());
    }
}
