package com.example.demo.backend.repository;


import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;

import javax.persistence.*;
import java.util.List;

public class ManagementRepository {
    private static EntityManagerFactory openConnectionEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("cma-prod");
    }
    public List<Management> getAllManagement() {
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        Query query = entityManager.createQuery("SELECT m FROM Management m", Management.class);
        List<Management> managements = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return managements;
    }

    public void createManagementUser(Management management) {
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(management);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public Management updateManagementUser(Management management) {
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
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
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
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
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
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

        return management;
    }


    public List<Client> getAllCustomers(){
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Query query = entityManager.createQuery("SELECT m FROM Client m", Client.class);
        List<Client> customers = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return customers;
    }

    public List<Product> getAllProducts(){
        EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        List<Product> products;
        Query query = entityManager.createQuery("SELECT m FROM Product m", Product.class);
        products = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return products;
    }

}
