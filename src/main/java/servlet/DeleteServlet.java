package servlet;

import dao.ClientDaoHibImpl;
import dao.DaoHibImpl;
import dao.ProductDaoHibImpl;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private static final DaoHibImpl clientDaoHib = new ClientDaoHibImpl();
    private static final DaoHibImpl productDaoHib = new ProductDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("clientId") != null) {
            deleteClient(req, resp);
        } else if (req.getParameter("productId") != null) {
            deleteProduct(req, resp);
        } else {
            req.setAttribute("error", "can't do operation");
            req.getRequestDispatcher("admin").forward(req, resp);
        }
    }

    private static void deleteClient(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        int result = clientDaoHib.delete(clientId);
        if (result > 0) {
            resp.sendRedirect("clientList");
        } else {
            req.setAttribute("error", "Error, can't delete " + clientId);
            req.getRequestDispatcher("clientList.jsp").forward(req, resp);
        }
    }

    private static void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        Product deleteProduct = (Product) productDaoHib.get(productId).get();
        int result = productDaoHib.delete(productId);
        if (result > 0) {
            resp.sendRedirect("productList");
        } else {
            req.setAttribute("error", "Error, can't delete " + deleteProduct);
            req.getRequestDispatcher("productList.jsp").forward(req, resp);
        }
    }
}
