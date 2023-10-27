package com.bank.Service;


import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;
import com.bank.Service.CreditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreditServiceTest {

    private CreditService creditService;

    @Mock
    private CreditDAOImpl creditDAOImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        creditService = new CreditService(); 
        creditService.setCreditDao(creditDAOImpl);
    }


    @Test
    public void testAddCredit() throws Exception {
        Credit credit = new Credit();
        credit.setValue(1000);
        credit.setStatus(CreditStatus.PENDING);
        when(creditDAOImpl.create(credit)).thenReturn(Optional.of(credit));

        Credit result = creditService.addCredit(credit);
        assertEquals(1000, result.getValue());
        assertEquals(CreditStatus.PENDING, result.getStatus());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        int creditId = 1;
        CreditStatus newStatus = CreditStatus.ACCEPTED;
        Credit credit = new Credit();
        credit.setId(creditId);
        credit.setStatus(CreditStatus.PENDING);

        when(creditDAOImpl.updateStatus(creditId, newStatus)).thenReturn(Optional.of(credit));

        Credit result = creditService.updateStatus(creditId, newStatus);
        assertEquals(creditId, result.getId());
        assertEquals(CreditStatus.ACCEPTED, result.getStatus());
    }

    @Test
    public void testFindByDate() throws Exception {
        LocalDate date = LocalDate.now();
        List<Credit> credits = new ArrayList<>();
        when(creditDAOImpl.findByDate(date)).thenReturn(credits);

        List<Credit> result = creditService.findByDate(date);
        assertEquals(credits, result);
    }

    @Test
    public void testFindByStatus() throws Exception {
        String status = "PENDING";
        List<Credit> credits = new ArrayList<>();
        when(creditDAOImpl.findByStatus(status)).thenReturn(credits);

        List<Credit> result = creditService.findByStatus(status);
        assertEquals(credits, result);
    }
}
