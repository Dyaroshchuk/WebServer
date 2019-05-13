package servlet;

import dao.ProductDao;
import dao.SqlProductDao;
import dao.SqlUserDao;
import dao.UserDao;
import model.Client;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/editTable")
public class EditTableServlet extends HttpServlet {

    private static final UserDao sqlUserDao = new SqlUserDao();
    private static final ProductDao sqlProductDao = new SqlProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("clientId") != null) {
            goToClientEditTable(req, resp);
        } else if (req.getParameter("productId") != null) {
            goToProductEditTable(req, resp);
        } else {
            req.setAttribute("error", "can't do operation");
            req.getRequestDispatcher("admin").forward(req, resp);
        }
    }

    private static void goToClientEditTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        Optional<Client> clientFromDB = sqlUserDao.getClientById(clientId);
        Client client = clientFromDB.get();
        req.setAttribute("client", client);
        req.getRequestDispatcher("editClient.jsp").forward(req, resp);
    }

    private static void goToProductEditTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        Product product = sqlProductDao.getProductById(productId).get();
        req.setAttribute("product", product);
        req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
    }
}
