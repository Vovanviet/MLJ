package dao;

import APIwithJson.Clouds;
import APIwithJson.Main;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MainDao {
    public void saveWeatherAPI(Main main){
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("insert into Main (temp,feels_like,temp_min,temp_max,pressure,humidity,sea_level,grnd_level)" +
                    "values(:m_temp,:m_feelslike,:m_tempmin,:m_tempmax,:m_pressure,:m_hum,:m_sea,:m_grnd)");
            query.setParameter("m_temp",main.getTemp());
            query.setParameter("m_feelslike",main.getFeelsLike());
            query.setParameter("m_tempmin",main.getTempMin());
            query.setParameter("m_tempmax",main.getTempMax());
            query.setParameter("m_pressure",main.getPressure());
            query.setParameter("m_hum",main.getHumidity());
            query.setParameter("m_sea",main.getSeaLevel());
            query.setParameter("m_grnd",main.getGrndLevel());
            query.executeUpdate();
//            session.save(main);
            session.getTransaction().commit();
            System.out.println("success");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
    public List<Main> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            List<Main> mainList= session.createQuery("select temp,feels_like,temp_min,temp_max,pressure,humidity,sea_level,grnd_level from Main ").getResultList();
            session.getTransaction().commit();
            return mainList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
