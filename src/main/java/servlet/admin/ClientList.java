package servlet.admin;

import dao.ClientDaoHibImpl;
import dao.ClientDao;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/clientList")
public class ClientList extends HttpServlet {

    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientDaoHib.getAll(Client.class);
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/admin/clientList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
