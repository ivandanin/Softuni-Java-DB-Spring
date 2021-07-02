import entities.Address;
import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static EntityManager entityManager;
    static Scanner scanner;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        entityManager = emf.createEntityManager();

        addNewAddress();

    }
public static void changeCasing() {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("UPDATE Town t " +
            "SET t.name = UPPER(t.name) " +
            "WHERE LENGTH(t.name) <= 5 ");
    System.out.println("rows affected -> " + query.executeUpdate());
    entityManager.getTransaction().commit();
}
public static void containsEmployee() {
        scanner = new Scanner(System.in);
        String[] fullName = scanner.nextLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

    Long result = entityManager.createQuery("SELECT count (e) from Employee e " +
                    "WHERE e.firstName = :f_name AND e.lastName = :l_name ", Long.class)
            .setParameter("f_name", firstName)
            .setParameter("l_name", lastName)
            .getSingleResult();
    System.out.println("Insert name of employee");
    System.out.println(result == 1 ? "Yes" : "No");
}
public static void salaryAbove() {
    List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e " +
            "WHERE e.salary > 50000 ", Employee.class)
            .getResultList();
    for (Employee employee : employeeList) {
        System.out.println(employee.getFirstName());
    }
}
public static void departmentSearch() {
    scanner = new Scanner(System.in);
    List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e " +
            "WHERE e.department.name = :d_name " +
            "ORDER BY e.salary, e.id ", Employee.class)
            .setParameter("d_name", "Research and Development")
            .getResultList();
    for (Employee employee : employeeList) {
        System.out.printf("%s %s from %s - $%.2f%n",employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());
    }
}
public static void addNewAddress() {
        scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.lastName = :l_name ", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

       createAddress("Vitoshka 16");


}

    private static Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }
}
