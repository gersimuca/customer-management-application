package com.example.demo.backend.repository;


import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;

import javax.persistence.*;
import java.util.List;

public class ProductRepository {
    public void createProduct(Product product) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public Product updateProduct(Product product) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return product;
    }

    public void delete(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public int getQuantity(Product product){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        Product sample = entityManager.find(Product.class, product.getProductId());
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return Integer.parseInt(sample.getQuantity());
    }

    public List<Product> getAllProducts(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
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

    public Product findProduct(Product theProduct){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        Product product = entityManager.find(Product.class, theProduct.getProductId());
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return product;
    }
}