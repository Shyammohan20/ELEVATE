package app.todo;

import java.sql.*;
import java.util.Scanner;

public class Task7 {

    static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";
    static final String PASSWORD = "00000000";

    static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addEmployee(String name, String position, double salary) {
        String sql = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("Inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewEmployees() {
        String sql = "SELECT * FROM employee";
        int c = 0;
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
            	c++;
                System.out.printf("\nID: %d | Name: %s | Position: %s | Salary: %.2f\n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("position"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (c == 0)
            System.out.println("No Employees found.");
        	
    }
    

    public static void updateEmployee(int id, String name, String position, double salary) {
        String sql = "UPDATE employee SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows == 0 ? "Not found." : "Updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows == 0 ? "Not found." : "Deleted." );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Details ---");
            System.out.println("1. Add");
            System.out.println("2. View");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Quit");
            System.out.print("Select Option : ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Position: "); String pos = sc.nextLine();
                    System.out.print("Salary: "); double sal = sc.nextDouble();
                    addEmployee(name, pos, sal);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.out.print("ID: "); int idU = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: "); String newName = sc.nextLine();
                    System.out.print("New Position: "); String newPos = sc.nextLine();
                    System.out.print("New Salary: "); double newSal = sc.nextDouble();
                    updateEmployee(idU, newName, newPos, newSal);
                    break;
                case 4:
                    System.out.print("ID: "); int idD = sc.nextInt();
                    deleteEmployee(idD);
                    break;
                case 5:
                    sc.close(); System.exit(0);
            }
        }
    }
}

