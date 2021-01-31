package it.unipi.dsmt.website;

import entities.Table;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "TablesServlet", value = "/TablesServlet")
public class TablesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Table> tables= new HashSet<>();

        tables.add(new Table("Tavolo1", 15, null));
        tables.add(new Table("Tavolo2", 10, null));
        tables.add(new Table("Tavolo3", 20, null));

        req.setAttribute("Tables", tables);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
