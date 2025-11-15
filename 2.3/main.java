// Develop Java programs using lambda expressions and stream operations for sorting, filtering, and processing large datasets efficiently.

// a)
// Write a program to sort a list of Employee objects (name, age, salary) using lambda expressions.

// b)
// Create a program to use lambda expressions and stream operations to filter students scoring above 75%, sort them by marks, and display their names.

// c)
// Write a Java program to process a large dataset of products using streams. Perform operations such as grouping products by category, finding the most expensive product in each category, and calculating the average price of all products. 

//a
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String toString() {
        return name + " " + age + " " + salary;
    }
}

public class SortEmployees {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Ayush", 21, 45000));
        list.add(new Employee("Riya", 23, 55000));
        list.add(new Employee("Karan", 22, 40000));

        list.sort((a, b) -> a.name.compareTo(b.name));
        list.forEach(System.out::println);

        list.sort((a, b) -> a.age - b.age);
        list.forEach(System.out::println);

        list.sort((a, b) -> Double.compare(a.salary, b.salary));
        list.forEach(System.out::println);
    }
}


//b

import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class FilterStudents {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("Ayush", 85),
                new Student("Rohan", 70),
                new Student("Sneha", 92),
                new Student("Tina", 60)
        );

        list.stream()
            .filter(s -> s.marks > 75)
            .sorted((a, b) -> b.marks - a.marks)
            .map(s -> s.name)
            .forEach(System.out::println);
    }
}


//c

import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String toString() {
        return name + " " + category + " " + price;
    }
}

public class ProductProcessing {
    public static void main(String[] args) {
        List<Product> list = Arrays.asList(
                new Product("Laptop", "Electronics", 55000),
                new Product("Phone", "Electronics", 30000),
                new Product("Shirt", "Clothing", 1500),
                new Product("Jeans", "Clothing", 2000),
                new Product("TV", "Electronics", 45000),
                new Product("Sofa", "Furniture", 25000),
                new Product("Table", "Furniture", 12000)
        );

        Map<String, List<Product>> groups = list.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        groups.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });

        Map<String, Optional<Product>> maxInCategory = list.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));

        maxInCategory.forEach((k, v) -> System.out.println(k + " " + v.get()));

        double avg = list.stream()
                .collect(Collectors.averagingDouble(p -> p.price));

        System.out.println(avg);
    }
}




