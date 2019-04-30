package servlet;

import dao.UserDao;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Client> clientFromDB = UserDao.getClientByName(login);
        if (clientFromDB.isPresent()) {
            Client client = clientFromDB.get();
            if (client.getPassword().equals(password)) {
                req.getSession().setAttribute("client", client);
                if (client.getRole().equals(Client.Role.ADMIN)) {
                    req.setAttribute("welcome", login + ", welcome to admin page");
                    req.getRequestDispatcher("/admin").forward(req, resp);
                } else {
                    req.setAttribute("welcome", login + ", welcome to your personal area");
                    req.getRequestDispatcher("personalArea.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "wrong login or password");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }

    }
}
