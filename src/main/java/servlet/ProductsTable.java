package servlet;

import dao.ClientDao;
import dao.ClientDaoHibImpl;
import dao.ProductDaoHibImpl;
import dao.ProductDao;
import model.Client;
import model.Order;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductsTable extends HttpServlet {

    private static final ProductDao productDaoHib = new ProductDaoHibImpl();
    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDaoHib.getAll(Product.class);
        req.setAttribute("products", products);
        Client clientFromSessiom = (Client) req.getSession().getAttribute("client");
        Client clientFromDB = clientDaoHib.get(Client.class, clientFromSessiom.getId()).get();
        Order clientOrder = clientFromDB.getOrder();

        if (clientOrder == null) {
            req.setAttribute("order", 0);
        } else {
            req.setAttribute("order", clientOrder.getProducts().size());
        }
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
