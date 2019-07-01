package pl.coderslab.controllers.customer;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.dao.OrderDao;
import pl.coderslab.db.dao.StatusDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/customerOrder")
public class CustomerOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = getIntParameter(request,"customerId");

        if (customerId!=null) {
            request.setAttribute("customerId", customerId);
            request.setAttribute("orders", new OrderDao<Integer>().readAllFor("customer",customerId));
        } else {
            request.setAttribute("orders", new OrderDao().readAll());
        }

        request.setAttribute("statusList", new StatusDao().readAll());
        request.setAttribute("customerList", new CustomerDao().readAll());
        getServletContext().getRequestDispatcher("/customerOrder.jsp").forward(request,response);
    }


    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }

}
