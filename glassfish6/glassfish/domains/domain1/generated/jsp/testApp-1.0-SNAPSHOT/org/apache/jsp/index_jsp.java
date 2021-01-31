package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"Mark Otto, Jacob Thornton, and Bootstrap contributors\">\n");
      out.write("    <meta name=\"generator\" content=\"Hugo 0.79.0\">\n");
      out.write("    <title>Starter Template Â· Bootstrap v5.0</title>\n");
      out.write("\n");
      out.write("    <link rel=\"canonical\" href=\"https://getbootstrap.com/docs/5.0/examples/starter-template/\">\n");
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Bootstrap core CSS -->\n");
      out.write("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        .bd-placeholder-img {\n");
      out.write("            font-size: 1.125rem;\n");
      out.write("            text-anchor: middle;\n");
      out.write("            -webkit-user-select: none;\n");
      out.write("            -moz-user-select: none;\n");
      out.write("            user-select: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media (min-width: 768px) {\n");
      out.write("            .bd-placeholder-img-lg {\n");
      out.write("                font-size: 3.5rem;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Custom styles for this template -->\n");
      out.write("    <link href=\"starter-template.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<nav class=\"navbar navbar-expand-md navbar-dark bg-dark fixed-top\">\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("        <a class=\"navbar-brand\" href=\"#\">Navbar</a>\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarsExampleDefault\"\n");
      out.write("                aria-controls=\"navbarsExampleDefault\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("            <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("        </button>\n");
      out.write("\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarsExampleDefault\">\n");
      out.write("            <ul class=\"navbar-nav me-auto mb-2 mb-md-0\">\n");
      out.write("                <li class=\"nav-item active\">\n");
      out.write("                    <a class=\"nav-link\" aria-current=\"page\" href=\"#\">Home</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Link</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link disabled\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Disabled</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item dropdown\">\n");
      out.write("                    <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"dropdown01\" data-bs-toggle=\"dropdown\"\n");
      out.write("                       aria-expanded=\"false\">Dropdown</a>\n");
      out.write("                    <ul class=\"dropdown-menu\" aria-labelledby=\"dropdown01\">\n");
      out.write("                        <li><a class=\"dropdown-item\" href=\"#\">Action</a></li>\n");
      out.write("                        <li><a class=\"dropdown-item\" href=\"#\">Another action</a></li>\n");
      out.write("                        <li><a class=\"dropdown-item\" href=\"#\">Something else here</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("            <form class=\"d-flex\">\n");
      out.write("                <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n");
      out.write("                <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("<main class=\"container\">\n");
      out.write("\n");
      out.write("    <div class=\"starter-template text-center py-5 px-3\">\n");
      out.write("        <h1>Bootstrap starter template</h1>\n");
      out.write("        <p class=\"lead\">Use this document as a way to quickly start any new project.<br> All you get is this text and a\n");
      out.write("            mostly barebones HTML document.</p>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</main><!-- /.container -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"../assets/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
