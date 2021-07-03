import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

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
removeAddress();
    }

    // ex 2
    private static void changeCasing() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Town t " +
                "SET t.name = UPPER(t.name) " +
                "WHERE LENGTH(t.name) <= 5 ");
        System.out.println("rows affected -> " + query.executeUpdate());
        entityManager.getTransaction().commit();
    }

    //ex 3
    private static void containsEmployee() {
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

    // ex 4
    private static void salaryAbove() {
        List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.salary > 50000 ", Employee.class)
                .getResultList();
        for (Employee employee : employeeList) {
            System.out.println(employee.getFirstName());
        }
    }

    // ex 5
    private static void departmentSearch() {
        scanner = new Scanner(System.in);
        List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name = :d_name " +
                "ORDER BY e.salary, e.id ", Employee.class)
                .setParameter("d_name", "Research and Development")
                .getResultList();
        for (Employee employee : employeeList) {
            System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(), employee.getLastName(),
                    employee.getDepartment(), employee.getSalary());
        }
    }

    // ex 6
    private static void addNewAddress() {
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

    // ex 6 - method
    private static Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }

    // ex 7
    private static void getEmployeeAddress() {
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

    // ex 8
    private static void getEmployeeWithProject() {
        scanner = new Scanner(System.in);
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

    // ex 9
    private static void getLastProjects() {
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

    // ex 10
    private static void increaseSalary()  {
        entityManager.getTransaction().begin();
        int affectedRows = entityManager.createQuery("UPDATE Employee e " +
                "SET e.salary = e.salary * 1.12 " +
                "WHERE e.department.id IN (1, 2, 4, 11)")
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.id " +
                "IN (1, 2, 4, 11) ", Employee.class)
                .getResultStream().forEach(employee -> {
            System.out.printf("%s %s ($%.2f)%n",
                    employee.getFirstName(), employee.getLastName(), employee.getSalary());
        });

    }

    // ex 11
    private static void findByFirstName() {
        scanner = new Scanner(System.in);
        String initials = scanner.nextLine();
        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.firstName LIKE :initials", Employee.class)
        .setParameter("initials", initials + "%")
        .getResultStream().forEach(employee -> {
        System.out.printf("%s %s - %s - ($%.2f)%n",
                employee.getFirstName(), employee.getLastName(),
                employee.getDepartment().getName(), employee.getSalary());
        });
    }

    // ex 12
    private static void getMaxSalary() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                "GROUP BY e.department.name " +
                "ORDER BY e.salary DESC ", Employee.class)
                .getResultStream()
                .forEach(department -> {
            System.out.printf("%s %.2f%n", department.getDepartment().getName(), department.getSalary());
        });
    }

    // ex 13
    private static void removeAddress() {
        scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        Town town = entityManager.createQuery("SELECT t FROM Town t " +
                "WHERE t.name = :t_name ", Town.class)
                .setParameter("t_name", townName)
                .getSingleResult();

        int affectedRows = removeAddressByTown(town.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(town);
        entityManager.getTransaction().commit();

        System.out.printf("%d address in %s deleted", affectedRows, townName);
    }
    
    // ex 13 - method
    private static int  removeAddressByTown(Integer id) {
        List<Address> addressList = entityManager.createQuery("SELECT a FROM Address a " +
                "WHERE a.town.id = :id", Address.class)
                .setParameter("id", id)
                .getResultList();

        entityManager.getTransaction().begin();
        addressList.forEach(entityManager::remove);
        entityManager.getTransaction().commit();

        return addressList.size();
    }
}
