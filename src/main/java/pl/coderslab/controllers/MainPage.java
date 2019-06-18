package pl.coderslab.controllers;

import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.dao.OrderDao;
import pl.coderslab.db.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class MainPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("test");

        // pobieramy zamowienia aktualnie realizowane Status 'W naprawie'
        request.setAttribute("orders", new OrderDao().readAllForStatus(3));
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
