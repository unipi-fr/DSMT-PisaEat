package website;

import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import ejbs.interfaces.ISingletonTableBean;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@WebServlet(name = "TableServlet", value = "/TableServlet")
public class TableServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @EJB
    ITableBean tableBean;

    @EJB
    ISingletonTableBean singletonTableBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("[DEBUG] inside the doPost method of TableServlet");

        String op = req.getParameter("operation");

        String name;

        HttpSession session = req.getSession(true);

        if (session.getAttribute("name") != null && session.getAttribute("bookSessionId") != null) {
            res.sendRedirect("BookingServlet");
            return;
        }

        switch (op) {
            case "bookOperation":
                name = req.getParameter("name");
                String tableId = req.getParameter("tableId");

                try {
                    BookSession bookSession = bookTable(tableId, name);

                    session.setAttribute("bookSessionId", bookSession.getId());
                    session.setAttribute("name", name);

                    res.sendRedirect("BookingServlet");
                } catch (TableAlreadyBookedException | BookSessionNotFoundException | TableNotFoundException | ExecutionException | InterruptedException e) {
                    logger.info("[DEBUG] " + e.getMessage());

                    req.setAttribute("errorMessage", e.getMessage());
                    getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
                }

                break;
            case "joinOperation":
                name = req.getParameter("name");
                String bookSessionId = req.getParameter("bookSessionId");
                String pin = req.getParameter("pin");

                try {
                    BookSession bookSession = joinTable(bookSessionId, name, pin);

                    session.setAttribute("bookSessionId", bookSession.getId());
                    session.setAttribute("name", name);

                    res.sendRedirect("BookingServlet");
                } catch (BookSessionNotFoundException | InvalidPinException | ExecutionException | InterruptedException e) {
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

    private BookSession bookTable(String tableId, String name) throws TableAlreadyBookedException, TableNotFoundException, BookSessionNotFoundException, ExecutionException, InterruptedException {
        logger.info("[DEBUG] inside the bookTable method of TableServlet");

        if (tableId == null || name == null) {
            throw new IllegalArgumentException();
        }

        Table table = singletonTableBean.bookTable(tableId, name).get();

        return tableBean.getBookSessionByTable(table);
    }

    private BookSession joinTable(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException, ExecutionException, InterruptedException {
        logger.info("[DEBUG] inside the joinTable method of TableServlet");

        if (bookSessionId == null || name == null || pin == null) {
            throw new IllegalArgumentException();
        }

        BookSession bookSession = singletonTableBean.joinBookSession(bookSessionId, name, pin).get();

        return bookSession;
    }
}
