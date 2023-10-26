package com.bank.Service;

import com.bank.DAO.CreditDAO;
import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

class CreditServiceTest {
    private static CreditDAOImpl creditDaoImpl;

    @BeforeAll
    public static void setUp() {

        creditDaoImpl = new CreditDAOImpl();
    }

    @Test
    void findByDate() {
        try {
            List<Credit> list = creditDaoImpl.findByDate(LocalDate.of(2023, 11, 11));
            Assertions.assertEquals(1, list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findByStatus() {
        String statusToFind = "APPROVED";

        List<Credit> credits = creditDaoImpl.findByStatus(statusToFind);

        Assertions.assertNotNull(credits);
        Assertions.assertFalse(credits.isEmpty());
        for (Credit credit : credits) {
            Assertions.assertEquals(CreditStatus.valueOf(statusToFind), credit.getStatus());
        }
    }
    }

