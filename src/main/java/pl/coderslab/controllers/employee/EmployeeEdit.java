package pl.coderslab.controllers.employee;

import pl.coderslab.db.dao.EmployeeDao;
import pl.coderslab.db.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/employeeEdit")
public class EmployeeEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String note = request.getParameter("note");
        // nie sprawdzamy poniewaz walidacja danych jest juz po stronie formularza.
        double hourCost = Double.parseDouble(request.getParameter("hourCost"));
        Employee employee = new Employee(firstName, lastName, address, phoneNumber, note, hourCost);
        employee.setId(id);

        try {
            new EmployeeDao().update(employee);
            response.sendRedirect(getServletContext().getContextPath()+"/employee/employeeList?msg=Zaktulizowano%20dane%20pracownika");
        } catch(RuntimeException ex){
            request.setAttribute("msg",ex.getMessage());
            request.setAttribute("employee",employee);
            getServletContext().getRequestDispatcher("/employeeEdit.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Integer employeeId = getIntParameter(request,"employeeId");

       if (employeeId!=null) {
           request.setAttribute("employee", new EmployeeDao().read(employeeId));
           getServletContext().getRequestDispatcher("/employeeEdit.jsp").forward(request,response);
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
