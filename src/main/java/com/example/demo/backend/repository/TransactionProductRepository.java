package com.example.demo.backend.repository;

import com.example.demo.backend.model.TransactionProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionProductRepository {

    public TransactionProduct insert(TransactionProduct transactionProduct){

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(transactionProduct);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return transactionProduct;
    }

    public TransactionProduct update(TransactionProduct transactionProduct){

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(transactionProduct);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return transactionProduct;
    }

    public void delete(Long id){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TransactionProduct transactionProduct = entityManager.find(TransactionProduct.class, id);
        entityManager.remove(transactionProduct);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
