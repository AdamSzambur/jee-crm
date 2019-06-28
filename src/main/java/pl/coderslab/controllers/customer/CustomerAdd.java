package pl.coderslab.controllers.customer;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/customerAdd")
public class CustomerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        Customer customer = new Customer(firstName, lastName, birthDate);

        try {
            new CustomerDao().create(customer);
            response.sendRedirect(getServletContext().getContextPath() + "/customer/customerList?msg=Dodano%20nowego%klienta%20do%20listy");
        } catch (RuntimeException ex) {
            request.setAttribute("customer", customer);
            request.setAttribute("msg", ex.getMessage());
            getServletContext().getRequestDispatcher("/customerAdd.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/customerAdd.jsp").forward(request, response);
    }

}
