package pl.coderslab.controllers.customer;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/customerDel")
public class CustomerDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = getIntParameter(request, "customerId");
        String msg;
        if (customerId != null) {
            try {
                new CustomerDao().delete(customerId);
                msg = "Usunieto%20klienta%20z%20bazy";
            } catch (RuntimeException ex) {
                msg = ex.getMessage();
            }
            response.sendRedirect(getServletContext().getContextPath() + "/customer/customerList?msg=" + msg);
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
