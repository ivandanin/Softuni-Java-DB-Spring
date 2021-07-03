import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static EntityManager entityManager;
    static Scanner scanner;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        entityManager = emf.createEntityManager();

getLastProjects();
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

       Address address = createAddress("Vitoshka 16");

       entityManager.getTransaction().begin();
       employee.setAddress(address);
       entityManager.getTransaction().commit();
}

    private static Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }
    public static void getEmployeeAddress() {
        List<Address> addressList = entityManager.createQuery("SELECT a FROM Address a " +
                "ORDER BY a.employees.size DESC ", Address.class)
                .setMaxResults(10)
                .getResultList();

        addressList.forEach(address -> {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                    address.getTown() == null
            ? "Unknown" : address.getTown().getName(),
                    address.getEmployees().size());
        });
    }
    public static void getEmployeeWithProject() {
        scanner = new Scanner(System.in);
        System.out.println("Enter valid employee id");
        int id = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.find(Employee.class, id);
        System.out.printf("%s %s - %s%n", employee.getFirstName(),
                employee.getLastName(), employee.getDepartment());
        employee.getProjects()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("\t%s%n", p.getName());
                });
    }
    public static void getLastProjects() {
        entityManager.createQuery("SELECT p FROM Project p " +
                "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream().sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.printf("Project name: %s%n\tProject Description: %s%n" +
                            "\tProject Start Date: %s%n\tProject End Date: %s%n",
                            project.getName(),
                            project.getDescription(),
                            project.getStartDate(),
                            project.getEndDate());
                });
    }
}
