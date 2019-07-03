package pl.coderslab.controllers.order;

import pl.coderslab.db.dao.*;
import pl.coderslab.db.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/orderEdit")
public class OrderEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean exit = false; // słabe to
        String action = request.getParameter("action");

        Order order = getArributesFromFormAndSetOrder(request);
        request.setAttribute("statusList", new StatusDao().readAll());
        request.setAttribute("employeeList", new EmployeeDao().readAll());
        request.setAttribute("customerList", new CustomerDao().readAll());
        request.setAttribute("vehicleList", new VehicleDao().readAllForCustomer(order.getCustomerId()));
        switch (action) {
            case "customer": {
                order.setVehicleId(0);
                break;
            }
            case "vehicle": {
                if (order.getVehicleId() != 0) {
                    order.setCustomerId(new VehicleDao().read(order.getVehicleId()).getCustomerId());
                    request.setAttribute("vehicleList", new VehicleDao().readAllForCustomer(order.getCustomerId()));
                }
                break;
            }
            case "employee": {
                if (order.getEmployeeId() != 0) {
                    order.setCostOfmanHour(new EmployeeDao().read(order.getEmployeeId()).getHourCost());
                } else {
                    order.setCostOfmanHour(0);
                }
                break;
            }
            case "update": {
                if (order.getVehicleId() != 0 && order.getEmployeeId() != 0) {
                    try {
                        new OrderDao().update(order);
                        exit = true;
                    } catch (RuntimeException ex) {
                        request.setAttribute("msg", ex.getMessage());
                    }
                } else {
                    request.setAttribute("msg", "Nieprawidłowo wypełniony arkusz ze zleceniem.");
                }
                break;
            }
        }

        if (!exit) {
            if (order.getVehicleId() == 0 && order.getCustomerId() == 0) {
                request.setAttribute("vehicleList", new VehicleDao().readAll());
            }
            request.setAttribute("order", order);
            request.getServletContext().getRequestDispatcher("/orderEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/order/orderList?msg=Zaktulizowano%20zlecenie");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderId = getIntParameter(request, "orderId");
        Integer customerId = getIntParameter(request, "customerId");
        Integer vehicleId = getIntParameter(request, "vehicleId");

        if (orderId != null) {
            Order order = new OrderDao().read(orderId);

            // ustawiamy tutaj parametry z adresu
            if (customerId != null) {
                order.setCustomerId(customerId);
                if (vehicleId != null) {
                    order.setVehicleId(vehicleId);
                } else {
                    order.setVehicleId(0);
                }
            }
            request.setAttribute("order", order);
            request.setAttribute("statusList", new StatusDao().readAll());
            request.setAttribute("employeeList", new EmployeeDao().readAll());
            request.setAttribute("customerList", new CustomerDao().readAll());
            request.setAttribute("vehicleList", new VehicleDao().readAllForCustomer(order.getCustomerId()));
            request.getServletContext().getRequestDispatcher("/orderEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/order/orderList");
        }
    }

    private static Order getArributesFromFormAndSetOrder(HttpServletRequest request) {
        Order order = new Order();
        Integer statusId = getIntParameter(request, "statusId");
        Integer orderId = getIntParameter(request, "orderId");
        Integer customerId = getIntParameter(request, "customerId");
        Integer vehicleId = getIntParameter(request, "vehicleId");
        Integer employeeId = getIntParameter(request, "employeeId");
        String dateDeliveredToRepair = request.getParameter("dateDeliveredToRepair");
        String datePlannedRepair = request.getParameter("datePlannedRepair");
        String dateStartedRepair = request.getParameter("dateStartedRepair");
        String problemDescription = request.getParameter("problemDescription");
        String repairDescription = request.getParameter("repairDescription");
        Double repairCostForCustommer = getDoubleParameter(request, "repairCostForCustommer");
        Double costOfParts = getDoubleParameter(request, "costOfParts");
        Double costOfmanHour = getDoubleParameter(request, "costOfmanHour");
        Integer manHour = getIntParameter(request, "manHour");
        if (statusId != null) order.setStatusId(statusId);
        if (orderId != null) order.setId(orderId);
        if (customerId != null) order.setCustomerId(customerId);
        if (vehicleId != null) order.setVehicleId(vehicleId);
        if (employeeId != null) order.setEmployeeId(employeeId);
        if (!dateDeliveredToRepair.equals("")) {
            order.setDateDeliveredToRepair(dateDeliveredToRepair);
        } else {
            order.setDateDeliveredToRepair(null);
        }
        if (!datePlannedRepair.equals("")) {
            order.setDatePlannedRepair(datePlannedRepair);
        } else {
            order.setDatePlannedRepair(null);
        }
        if (!dateStartedRepair.equals("")) {
            order.setDateStartedRepair(dateStartedRepair);
        } else {
            order.setDateStartedRepair(null);
        }
        order.setProblemDescription(problemDescription);
        order.setRepairDescription(repairDescription);
        if (repairCostForCustommer != null) order.setRepairCostForCustommer(repairCostForCustommer);
        if (costOfParts != null) order.setCostOfParts(costOfParts);
        if (costOfmanHour != null) order.setCostOfmanHour(costOfmanHour);
        if (manHour != null) order.setManHour(manHour);
        return order;
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Double getDoubleParameter(HttpServletRequest request, String parameterName) {
        try {
            return Double.parseDouble(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}
