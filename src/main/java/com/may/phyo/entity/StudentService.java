package com.may.phyo.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class StudentService {
    private EntityManager em;

    public StudentService(EntityManager em) {
        this.em = em;
    }
    public void createStudent(Student student){
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }
}
