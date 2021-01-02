package com.may.phyo.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class RunnerMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("criteria");
        EntityManager em = emf.createEntityManager();

        StudentService studentService = new StudentService(em);
        studentService.createStudent(new Student(1,"Aye Aye"));
        studentService.createStudent(new Student(2,"Zaw Zaw"));
        studentService.createStudent(new Student(3,"Aung Aung"));
        studentService.createStudent(new Student(4,"Kaung Kaung"));
        studentService.createStudent(new Student(5,"Wai Wai"));


        TypedQuery<Student> query = em.createQuery("select s from Student s where s.id > :id ",Student.class);
        List<Student> list = query.setParameter("id",3).getResultList();

        for (Student stu : list){
            System.out.println(stu);
        }

        System.out.println("Using Criteria Builder");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query1 = cb.createQuery(Student.class);
        Root<Student> student = query1.from(Student.class);
        query1.select(student);
        ParameterExpression<Integer> param = cb.parameter(Integer.class,"id");
        query1.where(cb.gt(student.get("id"),param));

        TypedQuery<Student> stu = em.createQuery(query1);
        stu.setParameter("id",4);
        list = stu.getResultList();

        for (Student s : list){
            System.out.println(s);
        }




        em.close();
        emf.close();
        com.may.phyo.util.JPAUtil.checkData("select * from Student");
    }
}
