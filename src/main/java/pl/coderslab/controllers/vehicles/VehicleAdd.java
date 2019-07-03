package pl.coderslab.controllers.vehicles;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.dao.VehicleDao;
import pl.coderslab.db.models.Employee;
import pl.coderslab.db.models.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/vehicle/vehicleAdd", "/customer/vehicleAdd", "/order/vehicleAdd"})
public class VehicleAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String carBrand = request.getParameter("carBrand");
        String model = request.getParameter("model");
        String productionYear = request.getParameter("productionYear");
        String registrationNumber = request.getParameter("registrationNumber");
        String nextTechnicalInspection = request.getParameter("nextTechnicalInspection");
        Integer vehicleCustomerId = getIntParameter(request,"vehicleCustomerId");
        Integer customerId = getIntParameter(request,"customerId");
        Integer orderId = getIntParameter(request,"orderId");

        Vehicle vehicle = new Vehicle(model,carBrand,productionYear,registrationNumber,nextTechnicalInspection,vehicleCustomerId);

        try {
            vehicle = new VehicleDao().create(vehicle);
            if (getCatalogueName(request).equals("customer")) {
                response.sendRedirect(getServletContext().getContextPath() + "/customer/customerVehicle?msg=Dodano%20nowy%20pojazd&customerId=" + customerId);
            }
            if (getCatalogueName(request).equals("vehicle")) {
                response.sendRedirect(getServletContext().getContextPath() + "/vehicle/vehicleList?msg=Dodanow%20nowy%20pojazd&customerId=" + customerId);
            }
            if (getCatalogueName(request).equals("order")) {
                response.sendRedirect(getServletContext().getContextPath() + "/order/orderEdit?orderId=" + orderId + "&customerId=" + customerId + "&vehicleId=" + vehicle.getId());
            }
        } catch (RuntimeException ex) {
            request.setAttribute("customerId", customerId);
            request.setAttribute("orderId", orderId);
            request.setAttribute("cat", getCatalogueName(request));
            request.setAttribute("customerList", new CustomerDao().readAll());
            request.setAttribute("msg", ex.getMessage());
            request.setAttribute("vehicle", vehicle);
            getServletContext().getRequestDispatcher("/vehicleAdd.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = getIntParameter(request,"customerId");
        Integer orderId = getIntParameter(request,"orderId");
        request.setAttribute("customerId", customerId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("cat", getCatalogueName(request));
        request.setAttribute("customerList", new CustomerDao().readAll());
        Vehicle vehicle = new Vehicle();
        if (customerId!=null) {
            vehicle.setCustomerId(customerId);
        }
        request.setAttribute("vehicle",vehicle);
        getServletContext().getRequestDispatcher("/vehicleAdd.jsp").forward(request,response);
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
