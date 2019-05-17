package POJO_classes;

import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class Lambdas
{

  public static final Function<HttpSession, Cookie> COOKIE_WITH_SESSION_ID = new Function<HttpSession, Cookie>() {
	public Cookie apply(HttpSession session) {
		return new Cookie("SessionID", session.getId());
	}
};

  public static final Function<String, Cookie> COOKIE_WITH_CSRF_ID = new Function<String, Cookie>() {
	public Cookie apply(String csrfToken) {
		return new Cookie("CSRF_TOKEN", csrfToken);
	}
};

}
