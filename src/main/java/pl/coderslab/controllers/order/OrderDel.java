package pl.coderslab.controllers.order;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/orderDel")
public class OrderDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderId = getIntParameter(request, "orderId");
        String msg;
        if (orderId != null) {
            try {
                new OrderDao().delete(orderId);
                msg = "Usunieto%20zlecenie%20z%20bazy";
            } catch (RuntimeException ex) {
                msg = ex.getMessage();
            }
            response.sendRedirect(getServletContext().getContextPath() + "/order/orderList?msg=" + msg);
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
