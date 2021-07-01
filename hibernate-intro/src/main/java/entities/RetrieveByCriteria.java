package entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RetrieveByCriteria {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Student> root = criteria.from(Student.class);
        criteria.select(root).where(builder.like(root.get("name"), "P%"));
        List<Student> studentList = session.createQuery(criteria).getResultList();
        for (Student student : studentList) {
            System.out.println(student.getName());
        }
        session.getTransaction().commit();
        session.close();
    }
}
