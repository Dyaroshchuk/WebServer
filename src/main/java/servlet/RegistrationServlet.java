package servlet;

import dao.UserDao;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Client newClient = new Client(login, password);

        if (UserDao.addClient(newClient) > 0) {
            req.setAttribute("login", login);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            //resp.getWriter().print(login + " welcome to our website");
        } else {
            resp.getWriter().print("User with name " + login + " already exist");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
}
