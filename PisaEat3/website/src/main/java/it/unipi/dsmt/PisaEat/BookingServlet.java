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
import webServiceClient.entities.BookSessionMessage;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @EJB
    ITableBean tableBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("[DEBUG] inside the doPost method of BookingServlet");

        String bookSessionId = (String) req.getSession(true).getAttribute("bookSessionId");
        String name = (String) req.getSession(true).getAttribute("name");

        if (name == null) {
            req.setAttribute("errorMessage", "Name not provided");
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }

        try {
            BookSession bookSession = tableBean.getBookSessionById(bookSessionId);

            BookSessionChat chat = ChatWebClient.getChat(bookSessionId);
            for (BookSessionMessage message : chat.List) {
                logger.info("[DEBUG][MESSAGE] " + message.toString());
            }

            req.setAttribute("bookSession", bookSession);
            getServletContext().getRequestDispatcher("/tablePage.jsp").forward(req, res);
        } catch (BookSessionNotFoundException e) {
            logger.info("[DEBUG] " + e.getMessage());

            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }
    }
}
