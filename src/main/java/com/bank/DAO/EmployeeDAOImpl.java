package com.bank.DAO;

import com.bank.Connection.Connection;
import com.bank.Entity.Agency;
import com.bank.Entity.Employee;
import com.bank.Exception.DeleteException;
import com.bank.Exception.InsertionException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeDAOImpl implements EmployeeDAO {

    private com.bank.Connection.Connection c = Connection.getInstance();
    private EntityManager entityManager = c.getManager();

    @Override
    @Transactional
    public Optional<Employee> create(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            if (employee == null)
                throw new Exception("***** Impossible d'ajouter un employee vide *****");
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
            return Optional.of(employee);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Employee> update(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            if (employee == null)
                throw new Exception("***** Impossible de modifier un employee vide *****");
            entityManager.merge(employee);
            entityManager.getTransaction().commit();
            return Optional.of(employee);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public int delete(int registrationNbr) {
        try {
            entityManager.getTransaction().begin();
            if (registrationNbr <= 0)
                throw new Exception("***** nombre d'ematricule non valide *****");
            Employee employee = entityManager.find(Employee.class, registrationNbr);
            entityManager.getTransaction().commit();
            if (employee != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(employee);
                entityManager.getTransaction().commit();
                return 1;
            } else {
                throw new DeleteException();
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Employee> findByRegistrationNbr(int registrationNbr) {
        try {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, registrationNbr);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(employee);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Employee>> findAll() {
        try {
            entityManager.getTransaction().begin();
            List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            entityManager.getTransaction().commit();
            return Optional.of(employees);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Employee>> find(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            List<Employee> employees = entityManager.createQuery(String.format("SELECT e FROM Employee e WHERE e.firstName LIKE :firstName AND e.lastName LIKE :lastName AND e.phone LIKE :phone AND e.address LIKE :address AND e.birthDay = :birthDay AND e.dateOfRecrutment = :dateOfRecrutment"), Employee.class)
                    .setParameter("firstName", "%" + employee.getFirstName() + "%")
                    .setParameter("lastName", "%" + employee.getLastName() + "%")
                    .setParameter("phone", "%" + employee.getPhone() + "%")
                    .setParameter("address", "%" + employee.getAddress() + "%")
                    .setParameter("birthDay", employee.getBirthDay())
                    .setParameter("dateOfRecrutment", employee.getDateOfRecrutment())
                    .getResultList();
            entityManager.getTransaction().commit();
            return Optional.of(employees);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Employee> changeAgency(Employee emp, String agencyCode) {
        try {
            entityManager.getTransaction().begin();
            if (emp.getAgency() == null)
                throw new Exception("***** LE CODE AGENCE DE L'AGENCE NE DOIT PAS ETRE VIDE *****");
            emp.setAgency(entityManager.find(Agency.class, agencyCode));
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            entityManager.merge(emp);
            entityManager.getTransaction().commit();
            return Optional.of(emp);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }
}
