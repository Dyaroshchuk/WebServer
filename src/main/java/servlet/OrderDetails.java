package servlet;

import dao.ClientDao;
import dao.ClientDaoHibImpl;
import model.Client;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/orderDetails")
public class OrderDetails extends HttpServlet {

    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client clientFromSession = (Client) req.getSession().getAttribute("client");
        Client clientFromDB = clientDaoHib.get(Client.class, clientFromSession.getId()).get();
        Order clientOrder = clientFromDB.getOrder();

        if (clientOrder == null) {
            req.setAttribute("message", "You don't have any items in your cart");
            req.getRequestDispatcher("/product").forward(req, resp);
        } else {
            req.setAttribute("order", clientOrder.getProducts());
            req.setAttribute("orderId", clientOrder.getId());
            req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
