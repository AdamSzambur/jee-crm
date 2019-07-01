package pl.coderslab.controllers;

import pl.coderslab.db.dao.*;
import pl.coderslab.db.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/orderDetails","/order/orderDetails","/vehicle/orderDetails","/employee/orderDetails","/customer/orderDetails"})
public class OrderDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderId = getIntParameter(request,"orderId");
        if (orderId != null) {
            Order order = new OrderDao().read(orderId);
            int customerId = new VehicleDao().read(order.getVehicleId()).getCustomerId();

            List<Vehicle> vehicleList = new VehicleDao().readAll();
            List<Employee> employeeList = new EmployeeDao().readAll();
            List<Status> statusList = new StatusDao().readAll();
            List<Customer> customerList = new CustomerDao().readAll();

            request.setAttribute("order", order);
            request.setAttribute("customerId", customerId);

            request.setAttribute("employeeList", employeeList);
            request.setAttribute("statusList", statusList);
            request.setAttribute("vehicleList", vehicleList);
            request.setAttribute("customerList", customerList);

            getServletContext().getRequestDispatcher("/orderDetails.jsp").forward(request,response);
        } else {
            request.setAttribute("msg", "Błąd : Nie podano indeksu zlecenia.");
            request.setAttribute("orders", new OrderDao().readAllFor("status_id",3));
            getServletContext().getRequestDispatcher("/mainPage.jsp").forward(request,response);
        }
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }

}
