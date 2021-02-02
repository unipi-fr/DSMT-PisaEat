package website;

import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import ejbs.interfaces.ITableBean;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        if(name == null){
            req.setAttribute("errorMessage", "Name not provided");
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }

        try {
            BookSession bookSession = tableBean.getBookSessionById(bookSessionId);

            req.setAttribute("bookSession", bookSession);
            getServletContext().getRequestDispatcher("/tablePage.jsp").forward(req, res);      
        } catch (BookSessionNotFoundException e) {
            logger.info("[DEBUG] " + e.getMessage());

            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }
    }
}