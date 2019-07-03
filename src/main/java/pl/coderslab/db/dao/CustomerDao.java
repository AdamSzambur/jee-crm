package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final String CREATE_QUERY =
            "INSERT INTO customer(firstname, lastname, birth_date, email) VALUES (?, ?, ?, ?)";
    private static final String READ_QUERY =
            "SELECT * FROM customer WHERE id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE customer SET firstname = ?, lastname = ?, birth_date = ?, email = ? where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM customer WHERE id = ?";

    private static final String READ_ALL_QUERY =
            "SELECT * FROM customer ORDER BY id";

    public Customer create(Customer customer) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getBirthDate());
            statement.setString(4, customer.getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
            }
            return customer;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Customer read(int customerId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFirstName(resultSet.getString("firstname"));
                customer.setLastName(resultSet.getString("lastname"));
                customer.setBirthDate(resultSet.getString("birth_date"));
                customer.setEmail(resultSet.getString("email"));
                return customer;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return null;
    }

    public void update(Customer customer) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getBirthDate());
            statement.setString(4, customer.getEmail());
            statement.setInt(5, customer.getId());
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }


    public void delete(int customerId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Customer> readAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<Customer> customers = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFirstName(resultSet.getString("firstname"));
                customer.setLastName(resultSet.getString("lastname"));
                customer.setBirthDate(resultSet.getString("birth_date"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
