package servlet.admin;

import dao.ClientGenericDao;
import dao.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/deleteClient")
public class DeleteClient extends HttpServlet {

    private static final GenericDao clientDaoHib = new ClientGenericDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        int result = clientDaoHib.delete(clientId);
        if (result > 0) {
            req.setAttribute("message", "The Client " + clientId + " was deleted");
            req.getRequestDispatcher("/admin/clientList").forward(req, resp);
        } else {
            req.setAttribute("message", "Error, can't delete " + clientId);
            req.getRequestDispatcher("/admin/clientList").forward(req, resp);
        }
    }
}
