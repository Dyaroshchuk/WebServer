package servlet;

import dao.ClientDaoHibImpl;
import dao.DaoHibImpl;
import dao.ProductDaoHibImpl;
import model.Client;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private static final DaoHibImpl clientDaoHib = new ClientDaoHibImpl();
    private static final DaoHibImpl productDaoHib = new ProductDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login") != null) {
            saveClientChanges(req, resp);
        } else if (req.getParameter("productId") != null) {
            saveProductChanges(req, resp);
        } else {
            req.setAttribute("error", "can't do operation");
            req.getRequestDispatcher("admin").forward(req, resp);
        }
    }

    private static void saveClientChanges(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        Optional<Client> clientFromDB = clientDaoHib.get(clientId);
        Client editingClient = clientFromDB.get();
        editingClient.setLogin(req.getParameter("login"));
        editingClient.setPassword(req.getParameter("password"));
        editingClient.setEmail(req.getParameter("email"));

        int result = clientDaoHib.edit(editingClient);
        if (result > 0) {
            resp.sendRedirect("clientList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("clientList").forward(req, resp);
        }
    }

    private static void saveProductChanges(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(productId, name, description, price);
        int result = productDaoHib.edit(product);
        if (result > 0) {
            resp.sendRedirect("productList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("productList").forward(req, resp);
        }

    }
}
