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
import java.util.Optional;

@WebServlet(value = "/admin/editClientForm")
public class EditClientForm extends HttpServlet {

    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        Optional<Client> clientFromDB = clientDaoHib.get(Client.class, clientId);
        Client client = clientFromDB.get();
        req.setAttribute("client", client);
        req.getRequestDispatcher("/admin/editClientForm.jsp").forward(req, resp);
    }
}
