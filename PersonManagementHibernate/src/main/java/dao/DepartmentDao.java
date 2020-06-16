package dao;

import model.DepartmentModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DepartmentDao {

    private SessionFactory sessionFactory;

    public DepartmentDao(){
        SessionFactorySingleton sessionFactorySingleton = SessionFactorySingleton.getInstance();
        sessionFactory = sessionFactorySingleton.getSessionFactory();
    }

    public void addDepartment(DepartmentModel departmentModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(departmentModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }

    public List<DepartmentModel> getAllDepartments(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<DepartmentModel> query = session.createQuery("from DepartmentModel", DepartmentModel.class);
        List<DepartmentModel> departments = query.getResultList();
        transaction.commit();
        if(session != null){
            session.close();
        }
        return departments;
    }

    public void updateDepartment(DepartmentModel departmentModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(departmentModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }

    public DepartmentModel findDepartmentById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DepartmentModel departmentModel = session.find(DepartmentModel.class, id);
        transaction.commit();
        if(session != null){
            session.close();
        }
        return departmentModel;
    }

    public void deleteDepartment(DepartmentModel departmentModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(departmentModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }
}
