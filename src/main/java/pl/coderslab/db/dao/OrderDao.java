package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao <T>{
    private static final String CREATE_QUERY =
            "INSERT INTO orders(date_delivered_to_repair, date_planned_repair, date_started_repair, employee_id, problem_description, reapir_description, status_id, vehicle_id, repair_cost_for_customer, cost_of_parts, cost_of_man_hour, man_hour) VALUES (?, ?, ?, ? , ?, ?,?,?,?,?,?,?)";
    private static final String READ_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id " +
            " where orders.id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE orders SET date_delivered_to_repair = ?, date_planned_repair = ? , date_started_repair = ?, employee_id = ?, problem_description = ?, reapir_description = ?, status_id = ?, vehicle_id = ?, repair_cost_for_customer = ?, cost_of_parts = ?, cost_of_man_hour = ?, man_hour = ? where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM orders WHERE id = ?";
    private static final String READ_ALL_FOR_STATUS_ID_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id " +
            " where status_id = ?";
    private static final String READ_ALL_FOR_EMPLOYEE_ID_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id " +
            " where employee_id = ?";
    private static final String READ_ALL_FOR_CUSTOMER_ID_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id " +
            " where customer.id=?";
    private static final String READ_ALL_FOR_VEHICLE_ID_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id " +
            "WHERE orders.vehicle_id = ?";
    private static final String READ_ALL_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id";

    private static final String READ_ALL_FILTER_QUERY =
            "SELECT orders.*, vehicle.car_brand, vehicle.model, vehicle.registration_number, " +
            "employee.firstname, employee.lastname, customer.firstname as customerFirstName, " +
            "customer.lastname as customerLastName, status.value FROM orders " +
            "JOIN vehicle ON orders.vehicle_id = vehicle.id " +
            "JOIN employee ON orders.employee_id = employee.id " +
            "JOIN customer ON customer.id = vehicle.customer_id " +
            "JOIN status ON status.id = orders.status_id " +
            "where concat(orders.problem_description, vehicle.car_brand, vehicle.model, vehicle.registration_number, employee.firstname,' ',employee.lastname, customer.firstname, ' ', customer.lastname, status.value) like ?";

    public Order create(Order order) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement = setStatment(statement,order);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            return order;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Order read(int orderId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return setOrder(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return null;
    }

    public void update(Order order) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement = setStatment(statement,order);
            statement.setInt(13, order.getId());
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int orderId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


        public List<Order> readAllFor(String caseName, T caseValue) {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();

            PreparedStatement statement;

            switch (caseName) {
            }

            switch (caseName) {
                case "employee":
                    statement = conn.prepareStatement(READ_ALL_FOR_EMPLOYEE_ID_QUERY);
                    break;
                case "customer":
                    statement = conn.prepareStatement(READ_ALL_FOR_CUSTOMER_ID_QUERY);
                    break;
                case "vehicle":
                    statement = conn.prepareStatement(READ_ALL_FOR_VEHICLE_ID_QUERY);
                    break;
                case "filter":
                    statement = conn.prepareStatement(READ_ALL_FILTER_QUERY);
                    break;
                default: {
                    // pobieramy status dla pierwszej strony
                    statement = conn.prepareStatement(READ_ALL_FOR_STATUS_ID_QUERY);
                }
            }
            if (caseValue instanceof Integer) {
                statement.setInt(1, (Integer)caseValue);
            } else {
                statement.setString(1, "%"+caseValue+"%");
            }



            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(setOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<Order> readAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(setOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    private static Order setOrder (ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setDateDeliveredToRepair(resultSet.getString("date_delivered_to_repair"));
        order.setDatePlannedRepair(resultSet.getString("date_planned_repair"));
        order.setDateStartedRepair(resultSet.getString("date_started_repair"));
        order.setEmployeeId(resultSet.getInt("employee_id"));
        order.setProblemDescription(resultSet.getString("problem_description"));
        order.setRepairDescription(resultSet.getString("repair_description"));
        order.setStatusId(resultSet.getInt("status_id"));
        order.setVehicleId(resultSet.getInt("vehicle_id"));
        order.setRepairCostForCustommer(resultSet.getDouble("repair_cost_for_customer"));
        order.setCostOfParts(resultSet.getDouble("cost_of_parts"));
        order.setCostOfmanHour(resultSet.getDouble("cost_of_man_hour"));
        order.setManHour(resultSet.getInt("man_hour"));
        order.setCarBrand(resultSet.getString("car_brand"));
        order.setModel(resultSet.getString("model"));
        order.setRegistrationNumber(resultSet.getString("registration_number"));
        order.setEmployeeFirstName(resultSet.getString("firstname"));
        order.setEmployeeLastName(resultSet.getString("lastname"));
        order.setCustomerFirstName(resultSet.getString("customerFirstName"));
        order.setCustomerLastName(resultSet.getString("customerLastName"));
        order.setStatusValue(resultSet.getString("value"));
        return order;
    }

    private static PreparedStatement setStatment(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.getDateDeliveredToRepair());
        statement.setString(2, order.getDatePlannedRepair());
        statement.setString(3, order.getDateStartedRepair());
        statement.setInt(4, order.getEmployeeId());
        statement.setString(5, order.getProblemDescription());
        statement.setString(6, order.getRepairDescription());
        statement.setInt(7, order.getStatusId());
        statement.setInt(8, order.getVehicleId());
        statement.setDouble(9, order.getRepairCostForCustommer());
        statement.setDouble(10, order.getCostOfParts());
        statement.setDouble(11, order.getCostOfmanHour());
        statement.setInt(12, order.getManHour());
        return statement;
    }
}
