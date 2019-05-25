package servlet;

import dao.ClientDao;
import dao.ClientDaoHibImpl;
import dao.OrderDao;
import dao.OrderDaoHibImpl;
import dao.ProductDao;
import dao.ProductDaoHibImpl;
import model.Client;
import model.Order;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(value = "/addToOrder")
public class AddToOrderServlet extends HttpServlet {

    private static final ProductDao productDaoHib = new ProductDaoHibImpl();
    private static final OrderDao orderDaoHib = new OrderDaoHibImpl();
    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Product product = productDaoHib.get(Product.class, productId).get();
        Client clientFromSession = (Client) req.getSession().getAttribute("client");
        Client clientFromDB = clientDaoHib.get(Client.class, clientFromSession.getId()).get();
        Order currentClientOrder = clientFromDB.getOrder();
        if (currentClientOrder == null) {
            Order order = new Order(Arrays.asList(product));
            clientFromDB.setOrder(order);
            clientDaoHib.update(clientFromDB);
        } else {
            currentClientOrder.getProducts().add(product);
            orderDaoHib.update(currentClientOrder);
        }
        resp.sendRedirect("/product");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
