package Servlets;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Keshani.A. Bogahawatte
 * IT17139786
 */

public class Home_Servlet extends HttpServlet{
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    String hiddenToken = request.getParameter("token");

    Optional<String> cookieValue = Stream.of(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("CSRF_TOKEN")).map(Cookie::getValue).findFirst();

    String csrfToken = cookieValue.get();

    if (csrfToken.equals(hiddenToken)) {
      response.getWriter().append("Success!"); // displays a success message if the csrf token and the hidden token is equal	
    }
    else {
      response.getWriter().append("ERROR!"); // displays an error message if the csrf token and the hidden token is not equal
    }
  }

}
