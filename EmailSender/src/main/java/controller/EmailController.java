package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.EmailUtil;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "EmailController", urlPatterns = {"/send"})
public class EmailController extends HttpServlet {
    static public Logger log = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message =  request.getParameter("message");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        String resultMessage = "";

        try {
            EmailUtil.send(to,subject, message, user, pass);
            resultMessage = "Sent message successfully....";
            log.info("The message was sent successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "Error: " + ex.getMessage();
            log.error("Error: the message wasn't sent");
        } finally {
            request.setAttribute("Message", resultMessage);
            request.getRequestDispatcher("pages/result.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}