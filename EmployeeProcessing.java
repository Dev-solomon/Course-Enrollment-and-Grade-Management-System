import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class EmployeeProcessing {
    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 35, "HR", 50000));
        employees.add(new Employee("Alice", 28, "Engineering", 60000));
        employees.add(new Employee("Bob", 40, "Finance", 70000));
        employees.add(new Employee("Emily", 32, "Marketing", 55000));

        // Function to concatenate name and department
        Function<Employee, String> concatNameAndDept = e -> e.getName() + " - " + e.getDepartment();

        // Generate a new collection with concatenated strings
        List<String> concatenatedList = employees.stream()
                                                .map(concatNameAndDept)
                                                .collect(Collectors.toList());

        // Print concatenated list
        System.out.println("Concatenated List:");
        concatenatedList.forEach(System.out::println);

        // Calculate average salary
        double averageSalary = employees.stream()
                                       .mapToDouble(Employee::getSalary)
                                       .average()
                                       .orElse(0);
        System.out.println("\nAverage Salary: " + averageSalary);

        // Filter employees above a certain age threshold
        int ageThreshold = 30;
        List<Employee> filteredEmployees = employees.stream()
                                                    .filter(e -> e.getAge() > ageThreshold)
                                                    .collect(Collectors.toList());

        // Print filtered employees
        System.out.println("\nEmployees above " + ageThreshold + " years old:");
        filteredEmployees.forEach(e -> System.out.println(e.getName() + " - " + e.getAge()));
    }
}

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    // Constructor, getters, and setters
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

