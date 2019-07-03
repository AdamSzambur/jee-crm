package pl.coderslab.controllers.vehicles;

import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/vehicle/vehicleDel", "/customer/vehicleDel"})
public class VehicleDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = getIntParameter(request, "customerId");
        Integer vehicleId = getIntParameter(request, "vehicleId");
        String msg;
        if (vehicleId != null) {
            try {
                new VehicleDao().delete(vehicleId);
                msg = "Usunieto%20pojazd%20z%20bazy";
            } catch (RuntimeException ex) {
                msg = ex.getMessage();
            }

            if (getCatalogueName(request).equals("customer")) {
                response.sendRedirect(getServletContext().getContextPath() + "/customer/customerVehicle?msg=" + msg + "&customerId=" + customerId);
            } else {
                response.sendRedirect(getServletContext().getContextPath() + "/vehicle/vehicleList?msg=" + msg + "&customerId=" + customerId);
            }

        }
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getCatalogueName(HttpServletRequest request) {
        String[] uriTab = (request.getRequestURI().split("/"));
        return uriTab[uriTab.length - 2];
    }

}
