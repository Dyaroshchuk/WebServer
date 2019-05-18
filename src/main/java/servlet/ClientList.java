package servlet;

import dao.ClientDaoHibImpl;
import dao.DaoHibImpl;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientList")
public class ClientList extends HttpServlet {

    private static final DaoHibImpl CLIENT_DAO_HIB = new ClientDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = CLIENT_DAO_HIB.getAll();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("clientList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
