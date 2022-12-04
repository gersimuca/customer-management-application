package com.example.demo;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@AfterAll
	private static EntityManagerFactory openConnectionEntityManagerFactory(){
		return Persistence.createEntityManagerFactory("cma-prod");
	}

	@Test
	public void testConnection() {
		EntityManagerFactory entityManagerFactory = openConnectionEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

}
