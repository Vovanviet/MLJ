package dao;

import APIwithJson.Clouds;
import APIwithJson.Main;
import APIwithJson.Wind;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class WindDao {
    public void saveMainAPI(Wind wind){
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
//            session.save(wind);
            Query query=session.createQuery("insert into Wind (speed,deg,gust)values(:w_speed,:w_deg,:w_gust)");
            query.setParameter("w_speed",wind.getSpeed());
            query.setParameter("w_deg",wind.getDeg());
            query.setParameter("w_gust",wind.getGust());
            query.executeUpdate();

            session.getTransaction().commit();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
    public List<Wind> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            List<Wind> windList= session.createQuery("select speed,deg,gust from Wind ").getResultList();
            session.getTransaction().commit();
            return windList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
