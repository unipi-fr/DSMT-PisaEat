package website;

import database.exceptions.TableNotFoundException;
import ejbs.interfaces.ITableBean;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "TableServlet", value = "/TableServlet")
public class TableServlet extends HttpServlet {

    @EJB
    ITableBean tableBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("[DEBUG] inside the doPost method of TableServlet");

        String name = req.getParameter("bookingName");
        String idTable = req.getParameter("idTable");

        try {
            tableBean.bookTable(idTable, name);
        } catch (TableNotFoundException e) {
            logger.info("[DEBUG] Table " + idTable + " Not Found");
        } catch (TableAlreadyBookedException e) {
            logger.info("[DEBUG] Table " + idTable + " Already Booked");
        }

        getServletContext().getRequestDispatcher("/HomeServlet").forward(req, res);
    }
}
