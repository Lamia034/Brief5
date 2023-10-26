package com.bank.DAO;

import com.bank.Connection.Connection;
import com.bank.Entity.Client;
import com.bank.Exception.DeleteException;
import com.bank.Exception.InsertionException;
import com.oracle.wls.shaded.org.apache.xpath.operations.Bool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientDAOImpl implements ClientDAO {

    private com.bank.Connection.Connection c = Connection.getInstance();
    private EntityManager entityManager = c.getManager();

    @Override
    @Transactional
    public boolean create(Client client) {
        try {
            entityManager.getTransaction().begin();
            if (client == null)
                throw new Exception("***** Impossible d'ajouter un client vide *****");
            entityManager.persist(client);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return false;
    }

    @Override
    @Transactional
    public int delete(int code) {
        try {
            entityManager.getTransaction().begin();
            if (code <= 0)
                throw new Exception("***** CODE CLIENT EST VIDE *****");
            Client client = entityManager.find(Client.class, code);
            entityManager.getTransaction().commit();
            if (client != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(client);
                entityManager.getTransaction().commit();
                return 1;
            } else {
                throw new DeleteException("***** AUCUN CLIENT N'EST SUPPRIMÉ *****");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return 0;
    }

    @Override
    @Transactional
    public Optional<Client> update(Client client) {
        try {
            entityManager.getTransaction().begin();
            if (client == null)
                throw new Exception("***** Impossible de modifier un client vide *****");
            entityManager.merge(client);
            entityManager.getTransaction().commit();
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByCode(int code) {
        try {
            entityManager.getTransaction().begin();
            Client client = entityManager.find(Client.class, code);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(client);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> findAll() {
        try {
            entityManager.getTransaction().begin();
            List<Client> clients = entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
            entityManager.getTransaction().commit();
            return Optional.ofNullable(clients);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> find(Client client) {
        try {
            entityManager.getTransaction().begin();
            List<Client> clients = entityManager.createQuery(String.format("SELECT c FROM Client c WHERE c.firstName LIKE :firstName AND c.lastName LIKE :lastName AND c.phone LIKE :phone AND c.address LIKE :address AND c.birthDay = :birthDay"), Client.class)
                    .setParameter("firstName", "%" + client.getFirstName() + "%")
                    .setParameter("lastName", "%" + client.getLastName() + "%")
                    .setParameter("phone", "%" + client.getPhone() + "%")
                    .setParameter("address", "%" + client.getAddress() + "%")
                    .setParameter("birthDay", java.sql.Date.valueOf(client.getBirthDay()))
                    .getResultList();
            entityManager.getTransaction().commit();
            return Optional.ofNullable(clients);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }
}
