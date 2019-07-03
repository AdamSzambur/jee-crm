package pl.coderslab.controllers.vehicles;

import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.dao.OrderDao;
import pl.coderslab.db.dao.StatusDao;
import pl.coderslab.db.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/vehicle/vehicleOrder", "/customer/vehicleOrder"})
public class VehicleOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer vehicleId = getIntParameter(request, "vehicleId");

        if (vehicleId != null) {
            request.setAttribute("vehicleId", vehicleId);
            request.setAttribute("orders", new OrderDao<Integer>().readAllFor("vehicle", vehicleId));
        } else {
            request.setAttribute("orders", new OrderDao().readAll());
        }
        request.setAttribute("statusList", new StatusDao().readAll());
        request.setAttribute("vehicleList", new VehicleDao().readAll());
        getServletContext().getRequestDispatcher("/vehicleOrder.jsp").forward(request, response);
    }


    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }

}
