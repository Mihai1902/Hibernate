package dao;

import model.PersonModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PersonDao {

    private SessionFactory sessionFactory;

    public PersonDao(){
        SessionFactorySingleton sessionFactorySingleton = SessionFactorySingleton.getInstance();
        sessionFactory = sessionFactorySingleton.getSessionFactory();
    }

    public void addPerson(PersonModel personModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(personModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }

    public List<PersonModel> getAllPeople(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<PersonModel> query = session.createQuery("from PersonModel", PersonModel.class);
        List<PersonModel> people = query.getResultList();
        transaction.commit();
        if(session != null){
            session.close();
        }
        return people;
    }

    public void updatePerson(PersonModel personModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(personModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }

    public PersonModel findPersonById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonModel personModel = session.find(PersonModel.class, id);
        transaction.commit();
        if(session != null){
            session.close();
        }
        return personModel;
    }

    public void deletePerson(PersonModel personModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(personModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }
}
