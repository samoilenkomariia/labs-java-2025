import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                System.err.println("Failed to connect to the database.");
                return;
            }

            boolean running = true;

            while (running) {
                System.out.println("\nChoose an action:");
                System.out.println("1 - Get all employees");
                System.out.println("2 - Get all tasks");
                System.out.println("3 - Get employees by department");
                System.out.println("4 - Add task for an employee");
                System.out.println("5 - Get tasks by employee");
                System.out.println("6 - Delete employee");
                System.out.println("0 - Exit");

                int choice = -1;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    scanner.nextLine();
                }

                switch (choice) {
                    case 1: // Get all employees
                        List<String> employees = DBQueries.getAllEmployees(conn);
                        if (employees.isEmpty()) {
                            System.out.println("No employees found");
                        } else {
                            employees.forEach(System.out::println);
                        }
                        break;

                    case 2: // Get all tasks
                        List<String> tasks = DBQueries.getAllTasks(conn);
                        if (tasks.isEmpty()) {
                            System.out.println("No tasks found");
                        } else {
                            tasks.forEach(System.out::println);
                        }
                        break;

                    case 3: // Get employees by department
                        System.out.print("Enter department ID: ");
                        if (scanner.hasNextInt()) {
                            int deptId = scanner.nextInt();
                            List<String> deptEmployees = DBQueries.getEmployeesByDept(conn, deptId);
                            if (deptEmployees.isEmpty()) {
                                System.out.println("No employees found in this department");
                            } else {
                                deptEmployees.forEach(System.out::println);
                            }
                        } else {
                            System.err.println("Invalid ID");
                            scanner.nextLine();
                        }
                        break;

                    case 4: // Add task for an employee
                        System.out.print("Enter employee id: ");
                        if (scanner.hasNextInt()) {
                            int empId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            System.out.print("Enter Task Description: ");
                            String description = scanner.nextLine();

                            List<String> addResult = DBQueries.addTask(conn, description, empId);
                            addResult.forEach(System.out::println);
                        } else {
                            System.err.println("Invalid ID");
                            scanner.nextLine();
                        }
                        break;

                    case 5: // Get tasks by employee
                        System.out.print("Enter Employee ID: ");
                        if (scanner.hasNextInt()) {
                            int empId = scanner.nextInt();
                            List<String> empTasks = DBQueries.getTasksByEmployee(conn, empId);
                            if (empTasks.isEmpty()) {
                                System.out.println("No tasks found for this employee.");
                            } else {
                                empTasks.forEach(System.out::println);
                            }
                        } else {
                            System.err.println("Invalid ID");
                            scanner.nextLine();
                        }
                        break;

                    case 6: // Delete employee [cite: 32]
                        System.out.print("enter ID of employee to delete: ");
                        if (scanner.hasNextInt()) {
                            int empId = scanner.nextInt();
                            DBQueries.deleteEmployee(conn, empId);
                        } else {
                            System.err.println("Invalid ID");
                            scanner.nextLine();
                        }
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        running = false;
                        break;

                    default:
                        System.err.println("Invalid option. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}