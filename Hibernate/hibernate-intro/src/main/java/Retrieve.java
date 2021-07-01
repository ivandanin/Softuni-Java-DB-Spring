import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Retrieve {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        List<Student> studentList = session.createQuery("FROM Student ", Student.class)
                .list();
        for (Student student : studentList) {
            System.out.println(student.getId());
        }
        session.getTransaction().commit();
        session.close();
    }
}
