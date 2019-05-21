package servlet;

import dao.DaoHibImpl;
import dao.ProductDaoHibImpl;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/admin/addProduct")
public class AddProduct extends HttpServlet {

    private static final DaoHibImpl PRODUCT_DAO_HIB = new ProductDaoHibImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(name, description, price);
        int result = PRODUCT_DAO_HIB.add(product);
        if (result > 0) {
            req.setAttribute("message", "The product " + name + " was added");
            req.getRequestDispatcher("productList").forward(req, resp);
        } else {
            req.setAttribute("message", "We can't add product " + name);
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
