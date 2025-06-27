import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. List Notes");
            System.out.println("3. Quit");
            System.out.print("Choose an option : ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Already Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 3);

        sc.close();
    }

    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE, true)) {
            writer.write(note + "\n");
            System.out.println("Note saved!");
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- Saved Notes ---\n");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
