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

@WebServlet(value = "/admin/editProductForm")
public class EditProductForm extends HttpServlet {

    private static final ProductDao productDaoHib = new ProductDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        Product product = productDaoHib.get(Product.class, productId).get();
        req.setAttribute("product", product);
        req.getRequestDispatcher("/admin/editProductForm.jsp").forward(req, resp);
    }
}
