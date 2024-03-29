package servlet;

import dao.ClientGenericDao;
import dao.GenericDao;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final GenericDao clientDaoHib = new ClientGenericDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Client newClient = new Client(login, password, email, "USER");

        if (clientDaoHib.add(newClient) > 0) {
            req.setAttribute("login", login);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("User with name " + login + " already exist");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
}
