package pl.coderslab.controllers.employee;

import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/employeeAdd")
public class EmployeeAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String note = request.getParameter("note");
        // nie sprawdzamy poniewaz walidacja danych jest juz po stronie formularza.
        double hourCost = Double.parseDouble(request.getParameter("hourCost"));
        Employee employee = new Employee(firstName, lastName, address, phoneNumber, note, hourCost);

        try {
            new EmployeeDao().create(employee);
            response.sendRedirect(getServletContext().getContextPath()+"/employee/employeeList?msg=Dodano%20nowego%20pracownika%20do%20listy");
        } catch(RuntimeException ex){
            request.setAttribute("employee",employee);
            request.setAttribute("msg",ex.getMessage());
            getServletContext().getRequestDispatcher("/employeeAdd.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/employeeAdd.jsp").forward(request,response);
    }

}
