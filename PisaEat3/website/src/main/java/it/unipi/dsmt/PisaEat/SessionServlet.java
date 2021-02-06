package it.unipi.dsmt.PisaEat;

import entities.BookSession;
import exceptions.BookSessionNotFoundException;
import interfaces.ITableBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webServiceClient.ChatWebClient;
import webServiceClient.entities.BookSessionChat;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "SessionServlet", value = "/SessionServlet")
public class SessionServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @EJB
    ITableBean tableBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("[DEBUG] inside the service method of SessionServlet");

        String bookSessionId = (String) req.getSession(true).getAttribute("bookSessionId");
        String name = (String) req.getSession(true).getAttribute("name");

        if (name == null) {
            req.setAttribute("errorMessage", "Name not provided");
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }

        try {
            BookSession bookSession = tableBean.getBookSessionById(bookSessionId);

            BookSessionChat chat = ChatWebClient.getChat(bookSessionId);

            req.setAttribute("bookSessionMessages", chat.ListMessages);
            req.setAttribute("sessionName", name);
            req.setAttribute("bookSession", bookSession);
            getServletContext().getRequestDispatcher("/tablePage.jsp").forward(req, res);
        } catch (BookSessionNotFoundException e) {
            logger.info("[DEBUG] " + e.getMessage());

            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("[DEBUG] inside the doPost method of SessionServlet");

        String bookSessionId = (String) req.getSession(true).getAttribute("bookSessionId");
        String name = (String) req.getSession(true).getAttribute("name");

        if (name == null) {
            req.setAttribute("errorMessage", "Name not provided");
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }
        if (bookSessionId == null) {
            req.setAttribute("errorMessage", "SessionID not provided");
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }

        String messageBody = req.getParameter("messageBody");

        ChatWebClient.sendMessage(bookSessionId, name, messageBody);
    }
}
