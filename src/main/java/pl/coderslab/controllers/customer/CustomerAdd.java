package pl.coderslab.controllers.customer;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/customer/customerAdd", "/order/customerAdd"})
public class CustomerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String email = request.getParameter("email");
        Customer customer = new Customer(firstName, lastName, birthDate, email);

        Integer orderId = getIntParameter(request, "orderId");

        try {
            customer = new CustomerDao().create(customer);
            if (getCatalogueName(request).equals("customer")) {
                response.sendRedirect(getServletContext().getContextPath() + "/customer/customerList?msg=Dodano%20nowego%klienta%20do%20listy");
            }
            if (getCatalogueName(request).equals("order")) {
                response.sendRedirect(getServletContext().getContextPath() + "/order/orderEdit?orderId=" + orderId + "&customerId=" + customer.getId());
            }
        } catch (RuntimeException ex) {
            request.setAttribute("customer", customer);
            request.setAttribute("msg", ex.getMessage());
            getServletContext().getRequestDispatcher("/customerAdd.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderId = getIntParameter(request, "orderId");
        request.setAttribute("orderId", orderId);
        request.setAttribute("cat", getCatalogueName(request));


        getServletContext().getRequestDispatcher("/customerAdd.jsp").forward(request, response);


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
