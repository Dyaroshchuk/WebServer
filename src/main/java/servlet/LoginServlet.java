package servlet;

import dao.UserDao;
import model.Client;
import utils.HashPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
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
        Client client = getClientFromOptional(UserDao.getClientByName(login), req, resp);
        String hashedPassword = HashPassword.getSecurePassword(password, client.getSalt());
            if (client.getPassword().equals(hashedPassword)) {
                req.getSession().setAttribute("client", client);
                redirectByRole(client, req, resp);
            } else {
                req.setAttribute("error", "wrong login or password");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

    }

    private static Client getClientFromOptional(Optional<Client> clientFromDB, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (clientFromDB.isPresent()) {
            return clientFromDB.get();
        } else {
            req.setAttribute("error", "wrong login or password");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            throw new NoSuchElementException("we don't have such client in DB");
        }
    }
    private static void  redirectByRole(Client client, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (client.getRole().equals(Client.Role.ADMIN)) {
            req.setAttribute("welcome", client.getLogin() + ", welcome to admin page");
            req.getRequestDispatcher("/admin").forward(req, resp);
        } else {
            req.setAttribute("welcome", client.getLogin() + ", welcome to our store");
            req.getRequestDispatcher("/product").forward(req, resp);
        }
    }
}
