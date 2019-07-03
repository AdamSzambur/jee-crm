package pl.coderslab.controllers.order;

import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/orderList")
public class OrderList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        if (filter != null) {
            request.setAttribute("filter", filter);
            request.setAttribute("orders", new OrderDao<String>().readAllFor("filter", filter));
        } else {
            request.setAttribute("orders", new OrderDao().readAll());
        }
        getServletContext().getRequestDispatcher("/orderList.jsp").forward(request, response);
    }
}
