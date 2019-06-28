package pl.coderslab.controllers.employee;

import pl.coderslab.db.dao.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/employeeList")
public class EmployeeList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", new EmployeeDao().readAll());
        getServletContext().getRequestDispatcher("/employeeList.jsp").forward(request,response);
    }
}
