// Develop Java programs using core concepts such as data structures, collections, and multithreading to manage and manipulate data.
// a) 
// Write a Java program to implement an ArrayList that stores employee details (ID, Name, and Salary). Allow users to add, update, remove, and search employees.

// b)
// Create a program to collect and store all the cards to assist the users in finding all the cards in a given symbol using Collection interface.

// c)
// Develop a ticket booking system with synchronized threads to ensure no double booking of seats. Use thread priorities to simulate VIP bookings being processed first.

// a)

import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return id + " " + name + " " + salary;
    }
}

public class EmployeeSystem {
    static ArrayList<Employee> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addEmployee() {
        int id = sc.nextInt();
        String name = sc.next();
        double salary = sc.nextDouble();
        list.add(new Employee(id, name, salary));
        System.out.println("Added");
    }

    public static void updateEmployee() {
        int id = sc.nextInt();
        for (Employee e : list) {
            if (e.id == id) {
                e.name = sc.next();
                e.salary = sc.nextDouble();
                System.out.println("Updated");
                return;
            }
        }
        System.out.println("Not Found");
    }

    public static void removeEmployee() {
        int id = sc.nextInt();
        Iterator<Employee> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                System.out.println("Removed");
                return;
            }
        }
        System.out.println("Not Found");
    }

    public static void searchEmployee() {
        int id = sc.nextInt();
        for (Employee e : list) {
            if (e.id == id) {
                System.out.println(e);
                return;
            }
        }
        System.out.println("Not Found");
    }

    public static void main(String[] args) {
        while (true) {
            int ch = sc.nextInt();
            if (ch == 1) addEmployee();
            else if (ch == 2) updateEmployee();
            else if (ch == 3) removeEmployee();
            else if (ch == 4) searchEmployee();
            else break;
        }
    }
}

//b

import java.util.*;

class Card {
    String symbol;
    String value;

    Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String toString() {
        return symbol + " " + value;
    }
}

public class CardCollection {
    public static void main(String[] args) {
        Collection<Card> cards = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while (n-- > 0) {
            String symbol = sc.next();
            String value = sc.next();
            cards.add(new Card(symbol, value));
        }

        String find = sc.next();
        for (Card c : cards) {
            if (c.symbol.equalsIgnoreCase(find)) {
                System.out.println(c);
            }
        }
    }
}


// c
class TicketCounter {
    private int seats = 1;

    public synchronized void bookTicket(String name) {
        if (seats > 0) {
            System.out.println(name + " booked the seat");
            seats--;
        } else {
            System.out.println(name + " seat not available");
        }
    }
}

class BookingThread extends Thread {
    TicketCounter counter;
    String name;

    BookingThread(TicketCounter counter, String name) {
        this.counter = counter;
        this.name = name;
    }

    public void run() {
        counter.bookTicket(name);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        BookingThread vip = new BookingThread(counter, "VIP");
        BookingThread user1 = new BookingThread(counter, "User1");
        BookingThread user2 = new BookingThread(counter, "User2");

        vip.setPriority(Thread.MAX_PRIORITY);
        user1.setPriority(Thread.NORM_PRIORITY);
        user2.setPriority(Thread.NORM_PRIORITY);

        vip.start();
        user1.start();
        user2.start();
    }
}

