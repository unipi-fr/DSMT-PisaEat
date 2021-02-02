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

@WebServlet(name = "TableServlet", value = "/TableServlet")
public class TableServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @EJB
    ITableBean tableBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("[DEBUG] inside the doPost method of TableServlet");

        String op = req.getParameter("operation");

        String name;

        switch (op) {
            case "bookOperation":
                name = req.getParameter("bookingName");
                String tableId = req.getParameter("tableId");

                try {
                    BookSession bookSession = bookTable(tableId, name);

                    req.setAttribute("bookSession", bookSession);
                    getServletContext().getRequestDispatcher("/tablePage.jsp").forward(req, res);
                } catch (TableAlreadyBookedException | BookSessionNotFoundException | TableNotFoundException e) {
                    logger.info("[DEBUG] " + e.getMessage());

                    req.setAttribute("errorMessage", e.getMessage());
                    getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
                }

                break;
            case "joinOperation":
                name = req.getParameter("bookingName");
                String bookSessionId = req.getParameter("bookSessionId");
                String pin = req.getParameter("pin");

                try {
                    BookSession bookSession = joinTable(bookSessionId, name, pin);

                    req.setAttribute("bookSession", bookSession);
                    getServletContext().getRequestDispatcher("/tablePage.jsp").forward(req, res);
                } catch (BookSessionNotFoundException | InvalidPinException e) {
                    logger.info("[DEBUG] " + e.getMessage());

                    req.setAttribute("errorMessage", e.getMessage());
                    getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
                }

                break;
            default:
                req.setAttribute("errorMessage", "Operation Not Valid");
                getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
        }
    }

    private BookSession bookTable(String tableId, String name) throws TableAlreadyBookedException, TableNotFoundException, BookSessionNotFoundException {
        logger.info("[DEBUG] inside the bookTable method of TableServlet");

        if (tableId == null || name == null) {
            throw new IllegalArgumentException();
        }

        Table table = tableBean.bookTable(tableId, name);

        return tableBean.getBookSessionByTable(table);
    }

    private BookSession joinTable(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException {
        logger.info("[DEBUG] inside the joinTable method of TableServlet");

        if (bookSessionId == null || name == null || pin == null) {
            throw new IllegalArgumentException();
        }

        BookSession bookSession = tableBean.getBookSessionById(bookSessionId);


        if (!bookSession.getPin().equals(pin)) {
            throw new InvalidPinException();
        }

        return bookSession;
    }
}
