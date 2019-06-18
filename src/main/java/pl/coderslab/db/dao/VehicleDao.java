package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static final String CREATE_QUERY =
            "INSERT INTO vehicle(model, car_brand, production_year, registration_number, next_technical_inspection, customer_id) VALUES (?, ?, ?, ? , ?, ?)";
    private static final String READ_QUERY =
            "SELECT * FROM vehicle WHERE id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE vehicle SET model = ?, car_brand = ?, production_year = ?, registration_number = ?, next_technical_inspection = ?, customer_id = ? where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM vehicle WHERE id = ?";

    private static final String READ_ALL_FOR_CUSTOMER_ID_QUERY =
            "SELECT * FROM vehicle WHERE customer_id=?";
    private static final String READ_ALL_QUERY =
            "SELECT * FROM vehicle";

    public Vehicle create(Vehicle vehicle) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, vehicle.getModel());
            statement.setString(2, vehicle.getCarBrand());
            statement.setString(3, vehicle.getProductionYear());
            statement.setString(4, vehicle.getRegistrationNumber());
            statement.setString(5, vehicle.getNextTechnicalInspection());
            statement.setInt(6, vehicle.getCustomerId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vehicle.setId(resultSet.getInt(1));
            }
            return vehicle;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Vehicle read(int vehicleId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setCarBrand(resultSet.getString("car_brand"));
                vehicle.setProductionYear(resultSet.getString("production_year"));
                vehicle.setRegistrationNumber(resultSet.getString("registration_number"));
                vehicle.setNextTechnicalInspection(resultSet.getString("next_technical_inspection"));
                vehicle.setCustomerId(resultSet.getInt("customer_id"));
                return vehicle;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return null;
    }

    public void update(Vehicle vehicle) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, vehicle.getModel());
            statement.setString(2, vehicle.getCarBrand());
            statement.setString(3, vehicle.getProductionYear());
            statement.setString(4, vehicle.getRegistrationNumber());
            statement.setString(5, vehicle.getNextTechnicalInspection());
            statement.setInt(6, vehicle.getCustomerId());
            statement.setInt(7, vehicle.getId());
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int vehicleId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, vehicleId);
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Vehicle> readAllForCustomer(int customerId) {
        try (Connection conn = DbUtil.getConnection()) {
            List<Vehicle> vehicles = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_FOR_CUSTOMER_ID_QUERY);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setCarBrand(resultSet.getString("car_brand"));
                vehicle.setProductionYear(resultSet.getString("production_year"));
                vehicle.setRegistrationNumber(resultSet.getString("registration_number"));
                vehicle.setNextTechnicalInspection(resultSet.getString("next_technical_inspection"));
                vehicle.setCustomerId(resultSet.getInt("customer_id"));
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Vehicle> readAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<Vehicle> vehicles = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setCarBrand(resultSet.getString("car_brand"));
                vehicle.setProductionYear(resultSet.getString("production_year"));
                vehicle.setRegistrationNumber(resultSet.getString("registration_number"));
                vehicle.setNextTechnicalInspection(resultSet.getString("next_technical_inspection"));
                vehicle.setCustomerId(resultSet.getInt("customer_id"));
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
