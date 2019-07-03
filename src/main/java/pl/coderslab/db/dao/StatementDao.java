package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatementDao {
    private static final String EMPLOYEE_HOURS_QUERY =
            "SELECT CONCAT(employee.firstname,' ',employee.lastname) as Imie_i_Nazwisko_Pracownika, sum(orders.man_hour) as Ilosc_roboczogodzin FROM orders JOIN employee ON orders.employee_id = employee.id WHERE date_started_repair between ? AND ? group by orders.employee_id ";

    private static final String PROFIT_NETTO_QUERY =
            "SELECT sum(repair_cost_for_customer-cost_of_parts-(cost_of_man_hour*man_hour)) as Zysk_NETTO FROM orders WHERE DATE_ADD(date_started_repair, INTERVAL orders.man_hour HOUR) between ? AND ?";


    public List<Map<String, Object>> readDataForStatement(String statementName, String dateFrom, String dateTo) {
        try (Connection conn = DbUtil.getConnection()) {
            List<Map<String, Object>> results = new ArrayList<>();
            PreparedStatement statement;
            if (statementName.equals("StatementEmployeeHours")){
                statement = conn.prepareStatement(EMPLOYEE_HOURS_QUERY);
            } else if (statementName.equals("StatementProfit")){
                statement = conn.prepareStatement(PROFIT_NETTO_QUERY);
            } else {
                throw new RuntimeException("Nie ma takiego zestawienia");
            }
            statement.setString(1,dateFrom);
            statement.setString(2,dateTo);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> result = new HashMap<>();
                for (int i = 1; i <= columnCount; i++ ) {
                    result.put(rsmd.getColumnLabel(i),resultSet.getString(rsmd.getColumnLabel(i)));
                }
                results.add(result);
            }
            return results;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }


}
