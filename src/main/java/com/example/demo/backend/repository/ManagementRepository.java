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
}
