package com.example.demo.backend.repository;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    public void updateClient(Client management) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.getTransaction().begin();
        entityManager.merge(management);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }


    public void createClient(Client client) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
