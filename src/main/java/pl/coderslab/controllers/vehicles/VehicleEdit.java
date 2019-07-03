package pl.coderslab.controllers.vehicles;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.VehicleDao;
import pl.coderslab.db.models.Customer;
import pl.coderslab.db.models.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/vehicle/vehicleEdit", "/customer/vehicleEdit"})
public class VehicleEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String carBrand = request.getParameter("carBrand");
        String model = request.getParameter("model");
        String productionYear = request.getParameter("productionYear");
        String registrationNumber = request.getParameter("registrationNumber");
        String nextTechnicalInspection = request.getParameter("nextTechnicalInspection");
        Integer vehicleCustomerId = getIntParameter(request,"vehicleCustomerId");
        Integer customerId = getIntParameter(request, "customerId");
        Vehicle vehicle = new Vehicle(model,carBrand,productionYear,registrationNumber,nextTechnicalInspection,vehicleCustomerId);
        vehicle.setId(id);

        try {
            new VehicleDao().update(vehicle);

            if (getCatalogueName(request).equals("customer")) {
                response.sendRedirect(getServletContext().getContextPath() + "/customer/customerVehicle?msg=Zaktulizowano%20dane%20pojazdu&customerId="+customerId);
            } else {
                response.sendRedirect(getServletContext().getContextPath() + "/vehicle/vehicleList?msg=Zaktulizowano%20dane%20pojazdu&customerId="+customerId);
            }
        } catch (RuntimeException ex) {
            request.setAttribute("msg", ex.getMessage());
            request.setAttribute("vehicle", vehicle);
            getServletContext().getRequestDispatcher("/vehicleEdit.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer vehicleId = getIntParameter(request, "vehicleId");
        Integer customerId = getIntParameter(request,"customerId");
        request.setAttribute("customerId", customerId);
        request.setAttribute("cat", getCatalogueName(request));

        if (vehicleId != null) {
            request.setAttribute("customerList", new CustomerDao().readAll());
            request.setAttribute("vehicle", new VehicleDao().read(vehicleId));
            getServletContext().getRequestDispatcher("/vehicleEdit.jsp").forward(request, response);
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
        return uriTab[uriTab.length-2];
    }
}
