package dao;

import model.DepartmentModel;
import model.PersonModel;
import model.SkillModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionFactorySingleton {

    private SessionFactory sessionFactory;
    private static SessionFactorySingleton sessionFactorySingleton;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static SessionFactorySingleton getSessionFactorySingleton() {
        return sessionFactorySingleton;
    }

    public static void setSessionFactorySingleton(SessionFactorySingleton sessionFactorySingleton) {
        SessionFactorySingleton.sessionFactorySingleton = sessionFactorySingleton;
    }

    private SessionFactorySingleton(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "pass");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.URL, "url");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(DepartmentModel.class);
        configuration.addAnnotatedClass(PersonModel.class);
        configuration.addAnnotatedClass(SkillModel.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactorySingleton getInstance(){
        if(sessionFactorySingleton == null){
            sessionFactorySingleton = new SessionFactorySingleton();
        }
        return sessionFactorySingleton;
    }
}
