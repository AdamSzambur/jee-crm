package pl.coderslab.controllers;

import pl.coderslab.db.dao.CustomerDao;
import pl.coderslab.db.dao.VehicleDao;
import pl.coderslab.db.models.Customer;
import pl.coderslab.db.models.Vehicle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@WebServlet("/sendNotify")
public class sendNotify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIntParameter(request,"id");
        String table = request.getParameter("table");
        String email = "";
        String title = "";
        String msg = "";


        if (id!=null) {

            if (table.equals("customer")) {
                Customer customer = new CustomerDao().read(id);
                email = customer.getEmail();
                title = "Zyczenia urodzinowe";
                msg = "Firma XXX zyczy Pani/Panu " + customer.getFirstName() + " " + customer.getLastName() + "\nwszystkiego najlepszego w dniu urodzin. 100 lat";
            }

            if (table.equals("vehicle")) {
                Vehicle vehicle = new VehicleDao().read(id);
                Customer customer = new CustomerDao().read(vehicle.getCustomerId());
                email = customer.getEmail();
                title = "Pamiętaj o przeglądzie techniczny auta.";
                msg = "Witamy serdecznie, \n Firma xxx przypomina o zblizającym się terminie przeglądu techniczne ("+
                        vehicle.getNextTechnicalInspection()+") samochodu "+vehicle.getModel()+" "+vehicle.getCarBrand()
                        +" o numerze rejestracyjnym "+vehicle.getRegistrationNumber()
                        +"\nPozdrawiamy XXX";
            }

            try {
                sendEmail(email,title,msg);
                if (table.equals("customer")) {
                    Customer customer = new CustomerDao().read(id);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(new Date());
                    customer.setBirthDateNotify(date);
                    new CustomerDao().update(customer);
                }
                if (table.equals("vehicle")) {
                    Vehicle vehicle = new VehicleDao().read(id);
                    vehicle.setInspectionNotify(vehicle.getNextTechnicalInspection());
                    new VehicleDao().update(vehicle);
                }
                response.sendRedirect(getServletContext().getContextPath()+"?msg=Wiadmosc%20zostala%20wyslana");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                response.sendRedirect(getServletContext().getContextPath()+"?msg="+ex.getMessage());
            }


        } else {
            response.sendRedirect(getServletContext().getContextPath());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }

    public void sendEmail(String email, String title, String msg) throws Exception {
        String smtpServer = getServletContext().getInitParameter("smtpServer");
        int port = Integer.parseInt(getServletContext().getInitParameter("port"));
        String userid = getServletContext().getInitParameter("userEmail");
        String password = getServletContext().getInitParameter("password");
        String contentType = "text/html";
        String subject = title;
        String from = getServletContext().getInitParameter("userEmail");
        String to = email;//some invalid address
        String bounceAddr = getServletContext().getInitParameter("userEmail");
        String body = msg;

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.from", bounceAddr);

        Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userid, password);
                    }
                });

        MimeMessage message = new MimeMessage(mailSession);
        message.addFrom(InternetAddress.parse(from));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(body, contentType);

        Transport transport = mailSession.getTransport();
        try {
            System.out.println("Sending ....");
            transport.connect(smtpServer, port, userid, password);
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            System.out.println("Sending done ...");
        } catch (Exception e) {
            System.err.println("Error Sending: ");
            e.printStackTrace();

        }
        transport.close();
    }
}
