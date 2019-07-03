package pl.coderslab.controllers.employee;

import pl.coderslab.db.dao.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/employeeDel")
public class EmployeeDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer employeeId = getIntParameter(request, "employeeId");
        String msg;
        if (employeeId != null) {
            try {
                new EmployeeDao().delete(employeeId);
                msg = "Usunieto%20pracownika%20z%20bazy";
            } catch (RuntimeException ex) {
                msg = ex.getMessage();
            }
            response.sendRedirect(getServletContext().getContextPath() + "/employee/employeeList?msg=" + msg);
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
