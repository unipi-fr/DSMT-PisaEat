package it.unipi.dsmt.PisaEat;

import entities.BookSession;
import entities.Table;
import exceptions.BookSessionNotFoundException;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import exceptions.TableNotFoundException;
import interfaces.ISingletonTableBean;
import interfaces.ITableBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@WebServlet(name = "TableServlet", value = "/TableServlet")
public class TableServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @EJB
    ITableBean tableBean;

    @EJB
    ISingletonTableBean singletonTableBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("[DEBUG] inside the doPost method of TableServlet");

        String op = req.getParameter("operation");

        String name;
        String bookSessionId;

        HttpSession session = req.getSession(true);

        if (session.getAttribute("name") != null && session.getAttribute("bookSessionId") != null && !op.equals("leaveOperation")) {
            res.sendRedirect("SessionServlet");
            return;
        }

        try {
            BookSession bookSession;
            switch (op) {
                case "bookOperation":
                    name = req.getParameter("name");
                    String tableId = req.getParameter("tableId");


                    bookSession = bookTable(tableId, name);

                    session.setAttribute("bookSessionId", bookSession.getId());
                    session.setAttribute("name", name);

                    res.sendRedirect("SessionServlet");


                    break;
                case "joinOperation":
                    name = req.getParameter("name");
                    bookSessionId = req.getParameter("bookSessionId");
                    String pin = req.getParameter("pin");


                    bookSession = joinTable(bookSessionId, name, pin);

                    session.setAttribute("bookSessionId", bookSession.getId());
                    session.setAttribute("name", name);

                    res.sendRedirect("SessionServlet");

                    break;
                case "leaveOperation":
                    logger.info("[DEBUG] inside the leave case of doPost Method of TableServlet");

                    bookSessionId = (String) req.getSession(true).getAttribute("bookSessionId");

                    if (bookSessionId == null) {
                        req.setAttribute("errorMessage", "bookSessionId not set");
                        getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
                        return;
                    }


                    leaveTable(bookSessionId);
                    session.invalidate();

                    res.sendRedirect("HomeServlet");
                    break;
                default:
                    req.setAttribute("errorMessage", "Operation Not Valid");
                    getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
            }
        } catch (BookSessionNotFoundException | InvalidPinException | ExecutionException | InterruptedException | TableNotFoundException | TableAlreadyBookedException e) {
            String message = e.getMessage();
            Throwable cause = e.getCause();
            while (cause != null) {
                message = cause.getMessage();
                cause = cause.getCause();
            }
            logger.info("[DEBUG] " + message);

            req.setAttribute("errorMessage", message);
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

        Future<BookSession> bookSession = singletonTableBean.joinBookSession(bookSessionId, name, pin);
        return bookSession.get();
    }

    private void leaveTable(String bookSessionId) throws TableNotFoundException {
        tableBean.leaveTable(bookSessionId);
    }
}
