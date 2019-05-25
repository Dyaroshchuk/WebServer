package utils;

import model.BuyCodeConfirmation;
import model.Client;
import model.Order;
import model.Product;
import model.Role;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static final Logger LOGGER = Logger.getLogger(HibernateSessionFactory.class);
    private static SessionFactory sessionFactory;

    public HibernateSessionFactory() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(BuyCodeConfirmation.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Order.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
        return sessionFactory;
    }
}
