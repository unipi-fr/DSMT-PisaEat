package it.unipi.dsmt.PisaEat;

import entities.Table;
import interfaces.ITableBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {

    @EJB
    ITableBean tableBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("[DEBUG] inside the service method of HomeServlet");

        Collection<Table> tables = tableBean.getTables();

        if(tables.isEmpty()){
            Table t1 = new Table("tavolo 1",7,null);
            t1 = tableBean.createTable(t1);
            Table t2 = new Table("tavolo 2",5,null);
            t2 = tableBean.createTable(t2);
            Table t3 = new Table("tavolo 3",4,null);
            t3 = tableBean.createTable(t3);
            Table t4 = new Table("tavolo 4",2,null);
            t4 = tableBean.createTable(t4);

            tables = tableBean.getTables();
        }

        String bookSessionId = (String) req.getSession().getAttribute("bookSessionId");

        req.setAttribute("Tables", tables);
        req.setAttribute("bookSessionId", bookSessionId);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
