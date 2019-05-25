package servlet;

import dao.ClientDao;
import dao.ClientDaoHibImpl;
import dao.CodeDaoHibImpl;
import dao.CodeDao;
import dao.ProductDaoHibImpl;
import dao.ProductDao;
import model.BuyCodeConfirmation;
import model.Client;
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

    private static final ProductDao productDaoHib = new ProductDaoHibImpl();
    private static final CodeDao codeDaoHib = new CodeDaoHibImpl();
    private static final ClientDao clientDaoHib = new ClientDaoHibImpl();
    private static final MailService mailService = new MailService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("orderId"));
        Client clientFromSession = (Client) req.getSession().getAttribute("client");
        Client clientFromDB = clientDaoHib.get(Client.class, clientFromSession.getId()).get();
        String codeValue = req.getParameter("buyCodeConfirmation");
        BuyCodeConfirmation buyCodeConfirmation = new BuyCodeConfirmation(codeValue, clientFromDB.getLogin(), orderId);
        Optional<BuyCodeConfirmation> codeFromDB = codeDaoHib.getCodeByOrderId(orderId);
        if (codeFromDB.isPresent() && codeFromDB.equals(Optional.of(buyCodeConfirmation))) {
            req.setAttribute("approved", "You have successfully made a purchase");
            req.getRequestDispatcher("/product").forward(req, resp);
        } else {
            req.setAttribute("error", "wrong Code confirmation");
            req.setAttribute("orderId", orderId);
            req.getRequestDispatcher("buyConfirmation.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("orderId"));
        Client clientFromSession = (Client) req.getSession().getAttribute("client");
        Client clientFromDB = clientDaoHib.get(Client.class, clientFromSession.getId()).get();
        String randomCode = RandomHelper.getRandomCode();
        mailService.sendMail(clientFromDB.getEmail(), randomCode);
        BuyCodeConfirmation buyCodeConfirmation = new BuyCodeConfirmation(randomCode, clientFromDB.getLogin(), orderId);
        codeDaoHib.add(buyCodeConfirmation);
        req.setAttribute("orderId", orderId);
        req.getRequestDispatcher("buyConfirmation.jsp").forward(req, resp);
    }
}
