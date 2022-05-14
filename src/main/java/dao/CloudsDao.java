package dao;

import APIwithJson.Clouds;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CloudsDao {

    public void saveCloudsAPI(Clouds clouds) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("insert into Clouds(all)values(:cloud_all)");
            query.setParameter("cloud_all",clouds.getAll());
            query.executeUpdate();
            //            session.save(clouds);

            session.getTransaction().commit();
            System.out.println("success");


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }


    public List<Clouds> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            List<Clouds> cloudsList= session.createQuery("select all from Clouds").getResultList();
            session.getTransaction().commit();
            return cloudsList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
