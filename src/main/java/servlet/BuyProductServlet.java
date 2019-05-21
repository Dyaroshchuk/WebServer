package servlet;

import dao.CodeGenericDao;
import dao.GenericDao;
import dao.ProductGenericDao;
import model.BuyCodeConfirmation;
import model.Client;
import model.Product;
import service.MailService;
import utils.RandomHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/buy")
public class BuyProductServlet extends HttpServlet {

    private static final GenericDao productDaoHib = new ProductGenericDao();
    private static final GenericDao codeDaoHib = new CodeGenericDao();
    private static final MailService mailService = new MailService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("good_id"));
        Client client = (Client) req.getSession().getAttribute("client");
        String codeValue = req.getParameter("buyCodeConfirmation");
        BuyCodeConfirmation buyCodeConfirmation = new BuyCodeConfirmation(codeValue, client.getLogin(), productId);
        Optional<BuyCodeConfirmation> codeFromeDB = codeDaoHib.get(codeValue);
        if (codeFromeDB.isPresent() && codeFromeDB.equals(Optional.of(buyCodeConfirmation))) {
            Product product = (Product) productDaoHib.get(productId).get();
            req.setAttribute("approved", "You are successful buy " + product.getName());
            req.getRequestDispatcher("/product").forward(req, resp);
        } else {
            req.setAttribute("error", "wrong Code confirmation");
            req.setAttribute("productId", productId);
            req.getRequestDispatcher("buyConfirmation.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Client client = (Client) req.getSession().getAttribute("client");
        String randomCode = RandomHelper.getRandomCode();
        mailService.sendMail(client.getEmail(), randomCode);
        BuyCodeConfirmation buyCodeConfirmation = new BuyCodeConfirmation(randomCode, client.getLogin(), productId);
        codeDaoHib.add(buyCodeConfirmation);
        req.setAttribute("productId", productId);
        req.getRequestDispatcher("buyConfirmation.jsp").forward(req, resp);
    }
}
