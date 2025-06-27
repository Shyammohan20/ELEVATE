import java.util.*;

class Book {
    int id;
    String name;
    boolean issued;

    Book(int id, String name) {
        this.id = id;
        this.name = name;
        this.issued = false;
    }
}

class User {
    int id;
    String name;
    ArrayList<Integer> borrowedBooks = new ArrayList<>();

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Library {
    HashMap<Integer, Book> bookList = new HashMap<>();
    HashMap<Integer, User> userList = new HashMap<>();

    void addBook(int id, String name) {
        if (!bookList.containsKey(id)) {
            bookList.put(id, new Book(id, name));
            System.out.println("Book added.");
        } else {
            System.out.println("Book ID exists.");
        }
    }

    void addUser(int id, String name) {
        if (!userList.containsKey(id)) {
            userList.put(id, new User(id, name));
            System.out.println("User added.");
        } else {
            System.out.println("User ID exists.");
        }
    }

    void issueBook(int userId, int bookId) {
        if (!bookList.containsKey(bookId)) {
            System.out.println("Book not found.");
            return;
        }
        if (!userList.containsKey(userId)) {
            System.out.println("User not found.");
            return;
        }

        Book b = bookList.get(bookId);
        User u = userList.get(userId);

        if (b.issued) {
            System.out.println("Book is already gave.");
        } else {
            b.issued = true;
            u.borrowedBooks.add(bookId);
            System.out.println("Book issued to " + u.name);
        }
    }

    void returnBook(int userId, int bookId) {
        if (!bookList.containsKey(bookId) || !userList.containsKey(userId)) {
            System.out.println("Invalid ID.");
            return;
        }

        Book b = bookList.get(bookId);
        User u = userList.get(userId);

        if (u.borrowedBooks.contains(bookId)) {
            b.issued = false;
            u.borrowedBooks.remove(Integer.valueOf(bookId));
            System.out.println("Book returned.");
        } else {
            System.out.println("Book not issued to this user.");
        }
    }

    void showAll() {
        System.out.println("--- Book List ---");
        for (Book b : bookList.values()) {
            System.out.println("ID: " + b.id + ", Name: " + b.name + ", Issued: " + b.issued);
        }

        System.out.println("--- User List ---");
        for (User u : userList.values()) {
            System.out.print("ID: " + u.id + ", Name: " + u.name + ", Books: ");
            for (int id : u.borrowedBooks) {
                System.out.print(id + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Give Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All");
            System.out.println("6. Quit");
            System.out.print("Enter option: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Book ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Book Name: ");
                String name = sc.nextLine();
                lib.addBook(id, name);
            } else if (choice == 2) {
                System.out.print("Enter User ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter User Name: ");
                String name = sc.nextLine();
                lib.addUser(id, name);
            } else if (choice == 3) {
                System.out.print("User ID: ");
                int uid = sc.nextInt();
                System.out.print("Book ID: ");
                int bid = sc.nextInt();
                lib.issueBook(uid, bid);
            } else if (choice == 4) {
                System.out.print("User ID: ");
                int uid = sc.nextInt();
                System.out.print("Book ID: ");
                int bid = sc.nextInt();
                lib.returnBook(uid, bid);
            } else if (choice == 5) {
                lib.showAll();
            }

        } while (choice != 6);

        sc.close();
    }
}