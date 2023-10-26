package com.bank.DAO;

import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CreditDAO {
    public Optional<Credit> create(Credit credit);
    public int delete(int id);
    public Optional<Credit> updateStatus(int id, CreditStatus status);
    public Optional<Credit> findById(int id);
    public List<Credit> findByDate(LocalDate date);
    public List<Credit> findByStatus(String status);
    public List<Credit> findAll();
}
