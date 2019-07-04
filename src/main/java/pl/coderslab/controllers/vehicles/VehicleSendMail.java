package pl.coderslab.controllers.vehicles;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/vehicle/vehicleSendMail")
public class VehicleSendMail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Dziala ale na razie nie będę tego uruchamiał :)
        try {
            sendEmail();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendEmail() throws Exception {
        String smtpServer = getServletContext().getInitParameter("smtpServer");
        int port = Integer.parseInt(getServletContext().getInitParameter("port"));
        String userid = getServletContext().getInitParameter("userEmail");
        String password = getServletContext().getInitParameter("password");
        String contentType = "text/html";
        String subject = "test: testujemy wysyłanie maila";
        String from = getServletContext().getInitParameter("userEmail");
        String to = "szambur@o2.pl";//some invalid address
        String bounceAddr = getServletContext().getInitParameter("userEmail");
        String body = "To jest testowa wiadomość wysłana z servleta";

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
