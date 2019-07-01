package pl.coderslab.controllers.customer;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/customerVehicle")
public class CustomerVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = getIntParameter(request,"customerId");
        if (customerId!=null) {
            request.setAttribute("customerId", customerId);
            request.setAttribute("vehicles", new VehicleDao().readAllForCustomer(customerId));
        } else {
            request.setAttribute("vehicles", new VehicleDao().readAll());
        }
        request.setAttribute("customerList", new CustomerDao().readAll());
        getServletContext().getRequestDispatcher("/customerVehicle.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}
