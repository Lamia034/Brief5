package com.bank.DAO;

import com.bank.Entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    public boolean create(Client client);
    public int delete(int code);
    public Optional<Client> update(Client client);

    public Optional<Client> findByCode(int code);
    public Optional<List<Client>> findAll();
    public Optional<List<Client>> find(Client client);
}
