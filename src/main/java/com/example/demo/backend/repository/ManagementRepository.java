package com.example.demo.backend.repository;


import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ManagementRepository {
    public List<Management> getAllManagement() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        List<Management> managements;
        Query query = entityManager.createQuery("SELECT m FROM Management m", Management.class);
        managements = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return managements;
    }

    public Management getManagementById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("filma24-em");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Management management = entityManager.find(Management.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return management;
    }

    public Management createManagementUser(Management management) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(management);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return management;
    }

    public Management updateManagementUser(Management management) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(management);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return management;
    }

    public void deleteManagementUser(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Management.class, id));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public Management login(String email, String password) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        List<Management> managements;
        Management management = null;

        Query query = entityManager.createQuery("SELECT m FROM Management m WHERE m.email = :email AND m.password = :password", Management.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        managements = query.getResultList();

        for (Management samples : managements) {
            if (samples.getEmail().equals(email) && samples.getPassword().equals(password)) {
                management = samples;
                break;
            }
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();

        return management != null ? management : null;
    }


    public List<Client> getAllCustomers(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        List<Client> customers;
        Query query = entityManager.createQuery("SELECT m FROM Client m", Client.class);
        customers = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return customers;
    }

//    public Customer getById(int id) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        entityTransaction.begin();
//
//        entityManager.getTransaction().begin();
//        Customer customer = entityManager.find(Customer.class, id);
//        entityManager.getTransaction().commit();
//
//        entityManager.close();
//        entityManagerFactory.close();
//        return customer;
//    }
//
//    public void deleteCustomer(int id) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        entityTransaction.begin();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(entityManager.find(Customer.class, id));
//        entityManager.getTransaction().commit();
//
//        entityManager.close();
//        entityManagerFactory.close();
//    }
}
