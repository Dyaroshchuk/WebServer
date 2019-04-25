package servlet;

import dao.UserDao;
import model.Client;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Client newClient = new Client(login, password);

        if (userDao.checkClient(newClient)) {
            resp.getWriter().print("Привет " + login);
        } else {
            resp.getWriter().print("неверный логин/пасс");
        }
    }
}
