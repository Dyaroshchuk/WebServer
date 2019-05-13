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

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        if (req.getParameter("login") != null) {
            addClient(req, resp);
        } else if (req.getParameter("name") != null) {
            addProduct(req, resp);
        } else {
            req.setAttribute("error", "can't do operation");
            req.getRequestDispatcher("admin").forward(req, resp);
        }
    }

    private static void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Long role = Long.parseLong(req.getParameter("role"));

        Client client = new Client(login, password, email, role);
        int result = UserDao.addClient(client);
        if (result > 0) {
            req.setAttribute("message", "The client " + login + " was added");
            req.getRequestDispatcher("clientList").forward(req, resp);
        } else {
            req.setAttribute("message", "The client " + login + " already exists");
            req.getRequestDispatcher("addClient.jsp").forward(req, resp);
        }
    }

    private static void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(name, description, price);
        int result = ProductDao.addProduct(product);
        if (result > 0) {
            req.setAttribute("message", "The product " + name + " was added");
            req.getRequestDispatcher("productList").forward(req, resp);
        } else {
            req.setAttribute("message", "We can't add product " + name);
            req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
        }
    }
}