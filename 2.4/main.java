// Create Java applications with JDBC for database connectivity, CRUD operations, and MVC architecture.

// a)
// Create a Java program to connect to a MySQL database and fetch data from a single table. The program should:

// Use DriverManager and Connection objects.

// Retrieve and display all records from a table named Employee with columns EmpID, Name, and Salary.

// b)
// Build a program to perform CRUD operations (Create, Read, Update, Delete) on a database table Product with columns:

// ProductID, ProductName, Price, and Quantity.
// The program should include:

// Menu-driven options for each operation.

// Transaction handling to ensure data integrity.

// c)
// Develop a Java application using JDBC and MVC architecture to manage student data. The application should:

// 1. Use a Student class as the model with fields like StudentID, Name, Department, and Marks.

// 2. Include a database table to store student data.

// 3. Allow the user to perform CRUD operations through a simple menu-driven view.

// 4. Implement database operations in a separate controller class.

//a

import java.sql.*;

public class FetchEmployee {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String pass = "password";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Employee");

            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + " " + name + " " + salary);
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//b

import java.sql.*;
import java.util.*;

public class ProductCRUD {
    static String url = "jdbc:mysql://localhost:3306/testdb";
    static String user = "root";
    static String pass = "password";

    public static void insertProduct(Connection con, Scanner sc) throws Exception {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");
        ps.setInt(1, sc.nextInt());
        ps.setString(2, sc.next());
        ps.setDouble(3, sc.nextDouble());
        ps.setInt(4, sc.nextInt());
        ps.executeUpdate();
        ps.close();
    }

    public static void viewProducts(Connection con) throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Product");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " " + 
                rs.getString(2) + " " + 
                rs.getDouble(3) + " " + 
                rs.getInt(4)
            );
        }
        rs.close();
        ps.close();
    }

    public static void updateProduct(Connection con, Scanner sc) throws Exception {
        PreparedStatement ps = con.prepareStatement("UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?");
        ps.setDouble(1, sc.nextDouble());
        ps.setInt(2, sc.nextInt());
        ps.setInt(3, sc.nextInt());
        ps.executeUpdate();
        ps.close();
    }

    public static void deleteProduct(Connection con, Scanner sc) throws Exception {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE ProductID=?");
        ps.setInt(1, sc.nextInt());
        ps.executeUpdate();
        ps.close();
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            con.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);

            while (true) {
                int ch = sc.nextInt();
                try {
                    if (ch == 1) insertProduct(con, sc);
                    else if (ch == 2) viewProducts(con);
                    else if (ch == 3) updateProduct(con, sc);
                    else if (ch == 4) deleteProduct(con, sc);
                    else if (ch == 5) break;
                    con.commit();
                } catch (Exception e) {
                    con.rollback();
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//c

// model: Student.java

public class Student {
    int id;
    String name;
    String dept;
    int marks;

    public Student(int id, String name, String dept, int marks) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.marks = marks;
    }
}

// Controller: StudentDAO.java

import java.sql.*;
import java.util.*;

public class StudentDAO {
    Connection con;

    public StudentDAO() throws Exception {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
    }

    public void add(Student s) throws Exception {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Students VALUES (?, ?, ?, ?)");
        ps.setInt(1, s.id);
        ps.setString(2, s.name);
        ps.setString(3, s.dept);
        ps.setInt(4, s.marks);
        ps.executeUpdate();
        ps.close();
    }

    public void display() throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Students");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " " +
                rs.getString(2) + " " +
                rs.getString(3) + " " +
                rs.getInt(4)
            );
        }
        rs.close();
        ps.close();
    }

    public void update(Student s) throws Exception {
        PreparedStatement ps = con.prepareStatement("UPDATE Students SET Name=?, Department=?, Marks=? WHERE StudentID=?");
        ps.setString(1, s.name);
        ps.setString(2, s.dept);
        ps.setInt(3, s.marks);
        ps.setInt(4, s.id);
        ps.executeUpdate();
        ps.close();
    }

    public void delete(int id) throws Exception {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Students WHERE StudentID=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}


// View: Student.java

import java.util.*;

public class StudentApp {
    public static void main(String[] args) throws Exception {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int ch = sc.nextInt();
            if (ch == 1) {
                int id = sc.nextInt();
                String name = sc.next();
                String dept = sc.next();
                int marks = sc.nextInt();
                dao.add(new Student(id, name, dept, marks));
            } 
            else if (ch == 2) dao.display();
            else if (ch == 3) {
                int id = sc.nextInt();
                String name = sc.next();
                String dept = sc.next();
                int marks = sc.nextInt();
                dao.update(new Student(id, name, dept, marks));
            } 
            else if (ch == 4) {
                int id = sc.nextInt();
                dao.delete(id);
            } 
            else if (ch == 5) break;
        }
    }
}
