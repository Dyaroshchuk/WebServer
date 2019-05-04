package servlet;

import dao.CodeDao;
import dao.ProductDao;
import model.Client;
import model.Code;
import model.Product;
import service.MailService;
import utils.RandomHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buy")
public class BuyProductServlet extends HttpServlet {

    private static final MailService mailService = new MailService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        Long productId = Long.parseLong(req.getParameter("good_id"));
        Client client = (Client) req.getSession().getAttribute("client");
        String codeValue = req.getParameter("code");
        Code code = new Code(codeValue, client.getLogin(), productId);
        if (CodeDao.checkCode(code)) {
            Product product = ProductDao.getProductById(productId).get();
            resp.getWriter().print("You buy " + product.getName());
        } else {
            resp.getWriter().print("wrong code confirmation");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Client client = (Client) req.getSession().getAttribute("client");
        String randomCode = RandomHelper.getRandomCode();
        mailService.sendMail(client.getEmail(), randomCode);
        Code code = new Code(randomCode, client.getLogin(), productId);
        CodeDao.addProduct(code);
        req.setAttribute("productId", productId);
        req.getRequestDispatcher("buyConfirmation.jsp").forward(req, resp);
    }
}
