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

@WebServlet(value = "/admin/updateProduct")
public class UpdateProduct extends HttpServlet {

    private static final ProductDao productDaoHib = new ProductDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(productId, name, description, price);
        int result = productDaoHib.update(product);
        if (result > 0) {
            resp.sendRedirect("/admin/productList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("/admin/productList").forward(req, resp);
        }
    }
}
