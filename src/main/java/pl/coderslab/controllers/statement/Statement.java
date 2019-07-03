package pl.coderslab.controllers.statement;

import pl.coderslab.db.dao.StatementDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/statement/Statement")
public class Statement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statement = request.getParameter("statement");
        String statementName = request.getParameter("statementName");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        if (dateFrom!=null) {
            try {
                request.setAttribute("statementRows", new StatementDao().readDataForStatement(statement,dateFrom,dateTo));
                request.setAttribute("statementName", statementName);
                request.setAttribute("dateFrom", dateFrom);
                request.setAttribute("dateTo", dateTo);
            } catch (RuntimeException ex) {
                request.setAttribute("msg", ex.getMessage());
            }
            getServletContext().getRequestDispatcher("/statement.jsp").forward(request, response);
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateTo = dateFormat.format(new Date());
            request.setAttribute("dateTo", dateTo);
            request.setAttribute("statementName", statementName);
            request.setAttribute("statement", statement);
            getServletContext().getRequestDispatcher("/statementDate.jsp").forward(request, response);
        }
    }
}
