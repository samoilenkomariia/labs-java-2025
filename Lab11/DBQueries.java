import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQueries {

    public static List<String> getAllEmployees(Connection conn) throws SQLException {
        List<String> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(rs.getString("first_name") + " " + rs.getString("last_name") +
                        " (" + rs.getString("position") + ")");
            }
        }
        return employees;
    }

    public static List<String> getAllTasks(Connection conn) throws SQLException {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(String.format("Task ID: %d | Desc: %s | Emp ID: %d",
                        rs.getInt("task_id"), rs.getString("description"), rs.getInt("employee_id")));
            }
        }
        return tasks;
    }

    public static List<String> getEmployeesByDept(Connection conn, int deptId) throws SQLException {
        List<String> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, deptId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(String.format("ID: %d | Name: %s %s",
                        rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name")));
            }
        }
        return employees;
    }

    public static List<String> addTask(Connection conn, String description, int empId) throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "INSERT INTO tasks (description, employee_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.setInt(2, empId);
            int rows = pstmt.executeUpdate();
            result.add("Tasks added: " + rows);
        }
        return result;
    }

    public static List<String> getTasksByEmployee(Connection conn, int empId) throws SQLException {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tasks.add("Task: " + rs.getString("description"));
            }
        }
        return tasks;
    }

    public static void deleteEmployee(Connection conn, int empId) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empId);
            int rows = pstmt.executeUpdate();
            System.out.println("Employees deleted: " + rows);
        }
    }
}