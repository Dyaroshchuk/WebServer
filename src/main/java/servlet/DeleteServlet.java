package servlet;

import dao.ProductDao;
import dao.SqlProductDao;
import dao.SqlUserDao;
import dao.UserDao;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private static final UserDao sqlUserDao = new SqlUserDao();
    private static final ProductDao sqlProductDao = new SqlProductDao();

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
        int result = sqlUserDao.deleteClient(clientId);
        if (result > 0) {
            resp.sendRedirect("clientList");
        } else {
            req.setAttribute("error", "Error, can't delete " + clientId);
            req.getRequestDispatcher("clientList.jsp").forward(req, resp);
        }
    }

    private static void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        Product deleteProduct = sqlProductDao.getProductById(productId).get();
        int result = sqlProductDao.deleteProduct(productId);
        if (result > 0) {
            resp.sendRedirect("productList");
        }
        else {
            req.setAttribute("error", "Error, can't delete " + deleteProduct);
            req.getRequestDispatcher("productList.jsp").forward(req, resp);
        }
    }
}
