package servlet.admin;

import dao.ClientGenericDao;
import dao.GenericDao;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/updateClient")
public class UpdateClient extends HttpServlet {

    private static final GenericDao clientDaoHib = new ClientGenericDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        Optional<Client> clientFromDB = clientDaoHib.get(clientId);
        Client editingClient = clientFromDB.get();
        editingClient.setLogin(req.getParameter("login"));
        editingClient.setPassword(req.getParameter("password"));
        editingClient.setEmail(req.getParameter("email"));

        int result = clientDaoHib.edit(editingClient);
        if (result > 0) {
            resp.sendRedirect("/admin/clientList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("/admin/clientList").forward(req, resp);
        }
    }
}
