import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double marks;
    private String addedOn;

    public Student(int id, String name, double marks, String addedOn) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.addedOn = addedOn;
    }

    public int getId() {
        return id;
    }

    public void updateDetails(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Marks: %.2f | Added: %s", id, name, marks, addedOn);
    }
}

public class StudentManager {
    private static final ArrayList<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addNewStudent();
                    case 2 -> displayAllStudents();
                    case 3 -> modifyStudent();
                    case 4 -> removeStudent();
                    case 5 -> {
                        System.out.println("Thank you. Exiting system.");
                        return;
                    }
                    default -> System.out.println("Invalid selection. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n======= Student Record Manager =======");
        System.out.println("1. Register New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Edit Student Info");
        System.out.println("4. Delete Student Record");
        System.out.println("5. Exit");
        System.out.print("Select option: ");
    }

    private static void addNewStudent() {
        try {
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (getStudentById(id) != null) {
                System.out.println("A student with this ID already exists.");
                return;
            }

            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(scanner.nextLine());

            String dateAdded = java.time.LocalDateTime.now().toString();

            studentList.add(new Student(id, name, marks, dateAdded));
            System.out.println("Student registered successfully.");
        } catch (Exception e) {
            System.out.println("Error while adding student. Please check your input.");
        }
    }

    private static void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            System.out.println("\n-- All Student Records --");
            studentList.forEach(System.out::println);
        }
    }

    private static void modifyStudent() {
        System.out.print("Enter the ID of student to edit: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = getStudentById(id);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter new marks: ");
            double newMarks = Double.parseDouble(scanner.nextLine());

            student.updateDetails(newName, newMarks);
            System.out.println("Student record updated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Update failed.");
        }
    }

    private static void removeStudent() {
        System.out.print("Enter ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = getStudentById(id);
            if (student != null) {
                studentList.remove(student);
                System.out.println("Student deleted.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Error during deletion.");
        }
    }

    private static Student getStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}