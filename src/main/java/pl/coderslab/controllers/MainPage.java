package pl.coderslab.controllers;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.OrderDao;
import pl.coderslab.db.dao.VehicleDao;
import pl.coderslab.db.models.Vehicle;

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
        // pobieramy liste aut dla ktorych nalezy wyslac przypomnienie o przeglądzie technicznym.
        request.setAttribute("inspectionList", new VehicleDao().inspectionNotify());
        // pobieramy liste klientow ktorzy maja dzisiaj urodziny
        request.setAttribute("birthdayList", new CustomerDao().birthDateNotify());

        // pobieramy zamowienia aktualnie realizowane Status 'W naprawie'
        request.setAttribute("orders", new OrderDao().readAllFor("status",3));
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
