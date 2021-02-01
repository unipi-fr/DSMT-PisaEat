package website;

import entities.Table;
import ejbs.interfaces.ITableBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "TablesServlet", value = "/TablesServlet")
public class TablesServlet extends HttpServlet {

    @EJB
    ITableBean tableBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Table> tables= new HashSet<>();

        Table testTable = new Table("testTableBean", 50, null);
        Table testTableReturn = tableBean.createTable(testTable);

        tables.add(testTableReturn);

        tables.add(new Table("Tavolo1", 15, null));
        tables.add(new Table("Tavolo2", 10, null));
        tables.add(new Table("Tavolo3", 20, null));

        req.setAttribute("Tables", tables);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
