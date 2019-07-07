package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final String CREATE_QUERY =
            "INSERT INTO employee(firstname, lastname, address, phone_number, note, hour_cost) VALUES (?, ?, ?, ? , ? ,?)";
    private static final String READ_QUERY =
            "SELECT * FROM employee WHERE id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE employee SET firstname = ?, lastname = ?, address = ?, phone_number = ?, note = ?, hour_cost = ? where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM employee WHERE id = ?";

    private static final String READ_ALL_QUERY =
            "SELECT * FROM employee ORDER BY id";

    public Employee create(Employee employee) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhoneNumber());
            statement.setString(5, employee.getNote());
            statement.setDouble(6, employee.getHourCost());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }
            return employee;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Employee read(int employeeId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = getEmployee(resultSet);
                return employee;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return null;
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("firstname"));
        employee.setLastName(resultSet.getString("lastname"));
        employee.setAddress(resultSet.getString("address"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setNote(resultSet.getString("note"));
        employee.setHourCost(resultSet.getDouble("hour_cost"));
        return employee;
    }

    public void update(Employee employee) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhoneNumber());
            statement.setString(5, employee.getNote());
            statement.setDouble(6, employee.getHourCost());
            statement.setInt(7, employee.getId());
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int employeeId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Employee> readAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<Employee> employees = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = getEmployee(resultSet);
                employees.add(employee);
            }
            return employees;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

}
