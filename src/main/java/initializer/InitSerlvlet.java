package initializer;

import dao.ClientDao;
import dao.ClientDaoHibImpl;
import dao.OrderDao;
import dao.OrderDaoHibImpl;
import dao.ProductDao;
import dao.ProductDaoHibImpl;
import dao.RoleDao;
import dao.RoleDaoHibImpl;
import model.Client;
import model.Order;
import model.Product;
import model.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitSerlvlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ClientDao clientDaoHib = new ClientDaoHibImpl();
        ProductDao productDaoHib = new ProductDaoHibImpl();
        OrderDao orderDaoHib = new OrderDaoHibImpl();
        RoleDao roleDaoHib = new RoleDaoHibImpl();
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("User");
        roleDaoHib.add(adminRole);
        roleDaoHib.add(userRole);

        Client admin = new Client("admin", "123", "test@test.net", adminRole);
        Client user = new Client("user", "123", "ydawork1@gmail.com", userRole);
        clientDaoHib.add(admin);
        clientDaoHib.add(user);

        Product pc = new Product("Комп", "Самый Мощный", 2000);
        Product laptop = new Product("Ноут","хрень моржовая", 250);
        Product refrigerator = new Product("Холодильник","Работает как печка", 1250);
        Product hren = new Product("хрен пойми что","хрен пойми зачем и как", 50);
        productDaoHib.add(pc);
        productDaoHib.add(laptop);
        productDaoHib.add(refrigerator);
        productDaoHib.add(hren);

        List<Product> products = new ArrayList<>();
        products.add(pc);
        products.add((laptop));
        Order order = new Order(products, admin);
        orderDaoHib.add(order);

    }
}
