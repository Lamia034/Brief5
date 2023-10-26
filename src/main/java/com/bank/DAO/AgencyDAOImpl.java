package com.bank.DAO;

import com.bank.Connection.Connection;
import com.bank.Entity.Agency;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AgencyDAOImpl implements AgencyDAO {
    private Connection c = Connection.getInstance();
    private EntityManager entityManager = c.getManager();

    @Override
    @Transactional
    public Optional<Agency> create(Agency agency) {
        try {
            entityManager.getTransaction().begin();
            if (agency == null)
                throw new Exception("***** Impossible d'ajouter une agence vide *****");
            entityManager.persist(agency);
            entityManager.getTransaction().commit();
            return Optional.of(agency);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public int delete(String code) {
        try {
            if (code.equals(""))
                throw new Exception("***** LE CODE DE L'AGENCE NE PEUT PAS ETRE VIDE *****");
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, code);
            entityManager.getTransaction().commit();
            if (agency != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(agency);
                entityManager.getTransaction().commit();
                return 1;
            } else {
                throw new Exception("***** AUCUNNE AGENCE N'EST SUPPRIMER *****");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return 0;
    }

    @Override
    @Transactional
    public Optional<Agency> update(Agency agency) {
        try {
            entityManager.getTransaction().begin();
            if (agency == null)
                throw new Exception("***** Impossible d'ajouter une agence vide *****");
            entityManager.merge(agency);
            entityManager.getTransaction().commit();
            return Optional.of(agency);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Agency> findByCode(String code) {
        try {
            if (code.equals(""))
                throw new Exception("***** LE CODE DE L'AGENCE NE PEUT PAS ETRE VIDE *****");
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, code);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(agency);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Agency>> find() {
        try {
            entityManager.getTransaction().begin();
            List<Agency> agencies = entityManager.createQuery("SELECT a FROM Agency a", Agency.class).getResultList();
            entityManager.getTransaction().commit();
            return Optional.ofNullable(agencies);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Agency> findByAddress(String address) {
        try {
            if (address.equals(""))
                throw new Exception("***** L'ADRESS DE L'AGENCE NE PEUT PAS ETRE VIDE *****");
            entityManager.getTransaction().begin();
            Agency agency = entityManager.createQuery("SELECT a FROM Agency a WHERE a.address = :address", Agency.class)
                    .setParameter("address", address)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return Optional.ofNullable(agency);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }
}
