package Servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.Database;
import POJO_classes.Lambdas;


/**
 * 
 */
public class Login_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String username = request.getParameter("username");
    String password = request.getParameter("password");
    HttpSession session = request.getSession(true); // create a new session if not exists

    if (Database.isValidUser(username, password))
    {
      String csrfToken = generateCSRFToken(session.getId());
      response.addCookie(Lambdas.COOKIE_WITH_SESSION_ID.apply(session));
      response.addCookie(Lambdas.COOKIE_WITH_CSRF_ID.apply(csrfToken));

      session.removeAttribute("invalidCredentials");
      response.sendRedirect("./Home_page.jsp");
    }
    else
    {
      session.setAttribute("invalidCredentials", "Not_ok");
      response.sendRedirect("./Login_page.jsp");
    }
	}
	
  private String generateCSRFToken(String strClearText)
  {
    return strClearText + "." + getRandomString();
  }

  private String getRandomString()
  {
    UUID randomUuid = UUID.randomUUID();
    return randomUuid.toString();
  }

}
