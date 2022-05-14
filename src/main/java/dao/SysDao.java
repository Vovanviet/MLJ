package dao;

import APIwithJson.Clouds;
import APIwithJson.Main;
import APIwithJson.Sys;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SysDao {
    public void saveMainAPI(Sys sys){
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("insert into Sys (type,id,country,sunrise,sunset)" +
                    "values(:s_type,:s_id,:s_coun,:s_sunrise,:s_sunset)");
            query.setParameter("s_type",sys.getType());
            query.setParameter("s_id",sys.getId());
            query.setParameter("s_coun",sys.getCountry());
            query.setParameter("s_sunrise",sys.getSunrise());
            query.setParameter("s_sunset",sys.getSunset());

            query.executeUpdate();
//            session.save(sys);
            session.getTransaction().commit();
            System.out.println("success");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
    public List<Sys> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            List<Sys> sysList= session.createQuery("select type,id,country,sunrise,sunset from Sys").getResultList();
            session.getTransaction().commit();
            return sysList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
