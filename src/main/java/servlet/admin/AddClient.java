package servlet.admin;

import dao.ClientDao;
import dao.ClientDaoHibImpl;
import model.Client;
import model.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/addClient")
public class AddClient extends HttpServlet {

    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Role role = new Role(Role.RoleType.valueOf(req.getParameter("role")));

        Client client = new Client(login, password, email, role);
        int result = clientDaoHib.add(client);
        if (result > 0) {
            req.setAttribute("message", "The client " + login + " was added");
            req.getRequestDispatcher("/admin/clientList").forward(req, resp);
        } else {
            req.setAttribute("message", "The client " + login + " already exists");
            req.getRequestDispatcher("/admin/addClientForm").forward(req, resp);
        }
    }
}
