package com.bank.Service;


import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.Client;
import com.bank.Entity.Credit;
import com.bank.Entity.Employee;
import com.bank.Enum.CreditStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreditServiceTest {

    @Mock
    private CreditDAOImpl creditDao;

    private CreditService creditService;
    private Credit credit;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        creditService = new CreditService(creditDao);
        creditService.setCreditDao(creditDao);
        credit = new Credit();
        credit.setModification_date(LocalDate.now());
        credit.setModification_time(LocalTime.MIDNIGHT);
        credit.setValue(1000);
        credit.setDuration(4);
        credit.setStatus(CreditStatus.PENDING);
        credit.setRemark("remark");
        credit.setLoanTax(Credit.TAUX);
        credit.setAgency(new Agency());
        credit.setEmployee(new Employee());
        credit.setClient(new Client());
    }

    @Test
    public void testAddCredit() throws Exception {

        when(creditDao.create(any(Credit.class))).thenReturn(Optional.of(credit));


        Credit result = creditService.addCredit(credit);


        assertEquals(1000, result.getValue());
        assertEquals(CreditStatus.PENDING, result.getStatus());
        verify(creditDao, times(1)).create(any(Credit.class));
    }

    @Test
    public void testUpdateStatus() throws Exception {
        int creditId = 1;
        CreditStatus newStatus = CreditStatus.ACCEPTED;
        Credit credit = new Credit();
        credit.setId(creditId);
        credit.setStatus(CreditStatus.ACCEPTED);

        when(creditDao.updateStatus(creditId, newStatus)).thenReturn(Optional.of(credit));

        Credit result = creditService.updateStatus(creditId, newStatus);
        assertEquals(creditId, result.getId());
        assertEquals(CreditStatus.ACCEPTED, result.getStatus());
    }

    @Test
    public void testFindByDate() throws Exception {
        LocalDate date = LocalDate.now();
        List<Credit> credits = new ArrayList<>();
        when(creditDao.findByDate(date)).thenReturn(credits);

        List<Credit> result = creditService.findByDate(date);
        assertEquals(credits, result);
    }

    @Test
    public void testFindByStatus() throws Exception {
        String status = "PENDING";
        List<Credit> credits = new ArrayList<>();
        when(creditDao.findByStatus(status)).thenReturn(credits);

        List<Credit> result = creditService.findByStatus(status);
        assertEquals(credits, result);
    }
}
