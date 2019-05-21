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

    private static final DaoHibImpl CLIENT_DAO_HIB = new ClientDaoHibImpl();
    private static final DaoHibImpl PRODUCT_DAO_HIB = new ProductDaoHibImpl();

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
        Optional<Client> clientFromDB = CLIENT_DAO_HIB.get(clientId);
        Client editingClient = clientFromDB.get();
        editingClient.setLogin(req.getParameter("login"));
        editingClient.setPassword(req.getParameter("password"));
        editingClient.setEmail(req.getParameter("email"));

        int result = CLIENT_DAO_HIB.edit(editingClient);
        if (result > 0) {
            resp.sendRedirect("/clientList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("/clientList").forward(req, resp);
        }
    }

    private static void saveProductChanges(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(productId, name, description, price);
        int result = PRODUCT_DAO_HIB.edit(product);
        if (result > 0) {
            resp.sendRedirect("/productList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("/productList").forward(req, resp);
        }

    }
}
