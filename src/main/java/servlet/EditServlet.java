package servlet;

import dao.ProductDao;
import dao.UserDao;
import model.Client;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        if (req.getParameter("login") != null) {
            saveClientChanges(req, resp);
        } else if (req.getParameter("productId") != null) {
            saveProductChanges(req, resp);
        } else {
            req.setAttribute("error", "can't do operation");
            req.getRequestDispatcher("admin").forward(req, resp);
        }
    }

    private static void saveClientChanges(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        Client editingClient = new Client(login, password, email);

        int result = UserDao.editClient(editingClient);
        if (result > 0) {
            resp.sendRedirect("clientList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("clientList").forward(req, resp);
        }
    }

    private static void saveProductChanges(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(productId, name, description, price);
        int result = ProductDao.editProduct(product);
        if (result > 0) {
            resp.sendRedirect("productList");
        } else {
            req.setAttribute("error", "we couldn't change client name");
            req.getRequestDispatcher("productList").forward(req, resp);
        }

    }
}
