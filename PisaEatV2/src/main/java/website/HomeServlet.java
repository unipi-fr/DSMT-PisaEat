package website;

import ejbs.interfaces.ISingletonTableBean;
import entities.Table;
import ejbs.interfaces.ITableBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {

    @EJB
    ITableBean tableBean;

    @EJB
    ISingletonTableBean singletonTableBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("[DEBUG] inside the service method of HomeServlet");

        Collection<Table> tables = tableBean.getTables();

        String bookSessionId = (String) req.getSession().getAttribute("bookSessionId");

        req.setAttribute("Tables", tables);
        req.setAttribute("bookSessionId", bookSessionId);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
