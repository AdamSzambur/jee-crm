package pl.coderslab.controllers.customer;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/customerEdit")
public class CustomerEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String email = request.getParameter("email");
        Customer customer = new Customer(firstName, lastName, birthDate, email);
        customer.setId(id);

        try {
            new CustomerDao().update(customer);
            response.sendRedirect(getServletContext().getContextPath() + "/customer/customerList?msg=Zaktulizowano%20dane%klienta");
        } catch (RuntimeException ex) {
            request.setAttribute("msg", ex.getMessage());
            request.setAttribute("customer", customer);
            getServletContext().getRequestDispatcher("/customerEdit.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = getIntParameter(request, "customerId");

        if (customerId != null) {
            request.setAttribute("customer", new CustomerDao().read(customerId));
            getServletContext().getRequestDispatcher("/customerEdit.jsp").forward(request, response);
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
