// Develop Java programs using autoboxing, serialization, file handling, and efficient data processing and management.

// a)
// Write a Java program to calculate the sum of a list of integers using autoboxing and unboxing. Include methods to parse strings into their respective wrapper classes (e.g., Integer.parseInt()).

// b)
// Create a Java program to serialize and deserialize a Student object. The program should:

// Serialize a Student object (containing id, name, and GPA) and save it to a file.

// Deserialize the object from the file and display the student details.

// Handle FileNotFoundException, IOException, and ClassNotFoundException using exception handling.

// c)
// Create a menu-based Java application with the following options. 1.Add an Employee 2. Display All 3. Exit If option 1 is selected, the application should gather details of the employee like employee name, employee id, designation and salary and store it in a file. If option 2 is selected, the application should display all the employee details. If option 3 is selected the application should exit

//a 

import java.util.*;

public class AutoBoxingSum {
    public static int calculateSum(List<Integer> list) {
        int sum = 0;
        for (Integer x : list) sum += x;
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        int n = sc.nextInt();
        while (n-- > 0) {
            String s = sc.next();
            Integer x = Integer.parseInt(s);
            list.add(x);
        }
        System.out.println(calculateSum(list));
    }
}


//b

import java.io.*;

class Student implements Serializable {
    int id;
    String name;
    double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        try {
            Student s = new Student(1, "Ayush", 8.9);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.dat"));
            out.writeObject(s);
            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.dat"));
            Student st = (Student) in.readObject();
            System.out.println(st.id + " " + st.name + " " + st.gpa);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO Error");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Error");
        }
    }
}


//c 

import java.io.*;
import java.util.*;

public class EmployeeFileApp {
    public static void addEmployee() {
        try {
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            String id = sc.next();
            String des = sc.next();
            double salary = sc.nextDouble();

            BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt", true));
            bw.write(name + "," + id + "," + des + "," + salary);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    public static void displayAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.txt"));
            String line;
            while ((line = br.readLine()) != null) System.out.println(line);
            br.close();
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int ch = sc.nextInt();
            if (ch == 1) addEmployee();
            else if (ch == 2) displayAll();
            else if (ch == 3) break;
        }
    }
}

