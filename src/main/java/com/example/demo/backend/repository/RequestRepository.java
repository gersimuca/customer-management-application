package com.example.demo.backend.repository;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;

import javax.persistence.*;
import java.util.List;

public class RequestRepository {
    public void createRequest(Client client , Requests request) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();


        Client dbClient = entityManager.find(Client.class, client.getId_client());
        dbClient.getRequests().add(request);
        entityManager.persist(request);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public List<Requests> getAllRequest(Client client) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        List<Requests> requests;
        Query query = entityManager.createNativeQuery("SELECT * FROM requests WHERE requests.fk_request = " + client.getId_client(), Requests.class);
        requests = query.getResultList();
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        return requests;
    }

    public void deleteRequest(long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cma-prod");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        Requests requests = entityManager.find(Requests.class, id);
        entityManager.remove(requests);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
