package dao;

import model.SkillModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SkillDao {

    private SessionFactory sessionFactory;

    public SkillDao(){
        SessionFactorySingleton sessionFactorySingleton = SessionFactorySingleton.getInstance();
        sessionFactory = sessionFactorySingleton.getSessionFactory();
    }

    public void addSkill(SkillModel skillModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skillModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }

    public List<SkillModel> getAllSkills(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SkillModel> query = session.createQuery("from SkillModel", SkillModel.class);
        List<SkillModel> skills = query.getResultList();
        transaction.commit();
        if(session != null){
            session.close();
        }
        return skills;
    }

    public void updateSkill(SkillModel skillModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(skillModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }

    public SkillModel findSkillById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SkillModel skillModel = session.find(SkillModel.class, id);
        transaction.commit();
        if(session != null){
            session.close();
        }
        return skillModel;
    }

    public void deleteSkill(SkillModel skillModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(skillModel);
        transaction.commit();
        if(session != null){
            session.close();
        }
    }
}
