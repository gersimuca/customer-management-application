package com.example.demo.backend.repository;


import com.example.demo.backend.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductRepository {
    public Product insert(Product product) {

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return product;
    }

    public Product update(Product product) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return product;
    }

    public void delete(Long id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}