package servlet.admin;

import dao.DaoHibImpl;
import dao.ProductDaoHibImpl;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/productList")
public class ProductList extends HttpServlet {

    private static final DaoHibImpl PRODUCT_DAO_HIB = new ProductDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = PRODUCT_DAO_HIB.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("productList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
