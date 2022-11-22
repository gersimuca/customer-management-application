package com.example.demo.backend.repository;


import com.example.demo.backend.model.Management;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ManagementRepository {


    public List<Management> getAllManagement() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        List<Management> managements = new ArrayList<>();

        entityTransaction.begin();

        Query query = entityManager.createQuery("SELECT m FROM Management m", Management.class);
        managements = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return managements;
    }

    public Management getManagementById(int id){
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

        entityManager.getTransaction().begin();
        entityManager.persist(management);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return management;
    }

    public Management updateManagementUser(Management management) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.getTransaction().begin();
        entityManager.merge(management);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return management;
    }

    public void deleteManagementUser(int id) {
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

    public Management login(String email, String password){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        entityTransaction.begin();
        List<Management> managements;
        Management management = new Management();
        Query query = entityManager.createQuery("SELECT m FROM Management m " +
                " WHERE m.email = :email AND m.password = :password", Management.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        managements = query.getResultList();
        if(!managements.isEmpty()) management = managements.get(0);
        entityTransaction.commit();

        return management != null ? management : null;
    }



    /*
    public Customer createCustomer(Customer customer) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return customer;
    }


    public List<Customer> getAllCustomers(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        List<Customer> customers = new ArrayList<>();

        entityTransaction.begin();

        Query query = entityManager.createQuery("SELECT m FROM Customer m", Customer.class);
        customers = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return customers;
    }

    public Customer getById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return customer;
    }

    public void deleteCustomer(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class, id));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
    */
}
