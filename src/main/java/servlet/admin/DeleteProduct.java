package servlet.admin;

import dao.ProductDaoHibImpl;
import dao.ProductDao;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/deleteProduct")
public class DeleteProduct extends HttpServlet {

    private static final ProductDao productDaoHib = new ProductDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        Product deleteProduct = productDaoHib.get(Product.class, productId).get();
        int result = productDaoHib.delete(Product.class, productId);
        if (result > 0) {
            req.setAttribute("message", "The Product " + deleteProduct + " was deleted");
            req.getRequestDispatcher("/admin/productList").forward(req, resp);
        } else {
            req.setAttribute("massage", "Error, can't delete " + deleteProduct);
            req.getRequestDispatcher("/admin/productList").forward(req, resp);
        }
    }
}
