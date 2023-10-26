package com.bank.DAO;

import com.bank.Connection.Connection;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;
import com.bank.Exception.InsertionException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class CreditDAOImpl implements CreditDAO {

    private Connection c = Connection.getInstance();
    private EntityManager entityManager = c.getManager();

//    public CreditDAOImpl(){
//        c = Connection.getInstance();
//        entityManager = c.getManager();
//    }
    @Override
    @Transactional
    public Optional<Credit> create(Credit credit) {
        try {
            entityManager.getTransaction().begin();
            if (credit == null)
                throw new Exception("***** LE CREDIT NE PEUT PAS ETRE VIDE *****");
            entityManager.persist(credit);
            entityManager.getTransaction().commit();
            return Optional.of(credit);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public int delete(int id) {
        try {
            entityManager.getTransaction().begin();
            if (id == 0)
                throw new Exception("***** ID DU CREDIT NE PEUT PAS ETRE 0 *****");
            Credit credit = entityManager.find(Credit.class, id);
            entityManager.getTransaction().commit();
            if (credit != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(credit);
                entityManager.getTransaction().commit();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return 0;
    }

    @Override
    @Transactional
    public Optional<Credit> updateStatus(int id, CreditStatus status) {
        try {
            entityManager.getTransaction().begin();
            if (id <= 0 || status == null)
                throw new Exception("***** STATUS|ID EST INVALIDE *****");
            Credit credit = entityManager.find(Credit.class, id);
            entityManager.getTransaction().commit();
            if (credit != null) {
                credit.setStatus(status);
                entityManager.getTransaction().begin();
                entityManager.merge(credit);
                entityManager.getTransaction().commit();
                return Optional.of(credit);
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Credit> findById(int id) {
        try {
            entityManager.getTransaction().begin();
            if (id <= 0)
                throw new Exception("***** ID EST INVALIDE *****");
            Credit credit = entityManager.find(Credit.class, id);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(credit);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Credit> findAll() {
        entityManager.getTransaction().begin();
        TypedQuery<Credit> query = entityManager.createQuery("SELECT c FROM Credit c", Credit.class);
        entityManager.getTransaction().commit();
        return query.getResultList();
    }
    @Override
    public List<Credit> findByDate(LocalDate date){
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Credit> query = entityManager.createQuery("SELECT c FROM Credit c WHERE c.modification_date = :date", Credit.class);
            query.setParameter("date", date);
            List<Credit> credits = query.getResultList();
            entityManager.getTransaction().commit();
            return credits;
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Credit> findByStatus(String status){
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Credit> query = entityManager.createQuery("SELECT c FROM Credit c WHERE c.status = :status", Credit.class);
            query.setParameter("status", CreditStatus.valueOf(status));
            List<Credit> credits = query.getResultList();
            entityManager.getTransaction().commit();
            return credits;
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        }
    }
}
