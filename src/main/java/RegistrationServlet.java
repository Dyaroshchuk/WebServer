import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Client newClient = new Client(login, password);

        if (ClientStorage.addUniqueClient(newClient)) {
            resp.getWriter().print(login + " welcome to our website");
        } else {
            resp.getWriter().print("User with " + login + " already exist ");
        }
    }
}
