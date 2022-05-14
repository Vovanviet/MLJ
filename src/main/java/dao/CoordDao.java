package dao;

import APIwithJson.Clouds;
import APIwithJson.Coord;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class CoordDao {
    public void saveCoordAPI(Coord coord){
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("insert into Coord (lat,lon)values(:c_lat,:c_lon)");
            query.setParameter("c_lat",coord.getLat());
            query.setParameter("c_lon",coord.getLon());
            query.executeUpdate();
//            session.save(coord);
            session.getTransaction().commit();
            System.out.println("success");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
    public List<Coord> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            String sql="select lon, lat from Coord ";
            NativeQuery query = session.createQuery(sql);
            session.getTransaction().commit();
            return cloudsList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
    public static Long safeToLong(Object obj1) {
        return safeToLong(obj1, null);
    }

    public static Long safeToLong(Object obj1, Long defaultValue) {
        Long result = defaultValue;
        if (obj1 != null) {
            if (obj1 instanceof BigDecimal) {
                return ((BigDecimal) obj1).longValue();
            }
            if (obj1 instanceof BigInteger) {
                return ((BigInteger) obj1).longValue();
            }

        }
