import entities.Engine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();


        // change casing
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Town t " +
                "SET t.name = UPPER(t.name) " +
                "WHERE LENGTH(t.name) <= 5 ");
        System.out.println("rows affected " + query.executeUpdate());
        entityManager.getTransaction().commit();
}


}
