package servlet;

import dao.UserDao;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/editTable")
public class EditTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Client> clientFromDB = UserDao.getClientByName(req.getParameter("name"));
        Client client = clientFromDB.get();
        req.setAttribute("client", client);
        req.getRequestDispatcher("editClient.jsp").forward(req, resp);
    }
}
