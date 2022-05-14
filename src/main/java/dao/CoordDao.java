package dao;

import APIwithJson.Clouds;
import APIwithJson.Coord;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoordDao {
    public void saveCoordAPI(Coord coord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("insert into Coord (lat,lon)values(:c_lat,:c_lon)");
            query.setParameter("c_lat", coord.getLat());
            query.setParameter("c_lon", coord.getLon());
            query.executeUpdate();
//            session.save(coord);
            session.getTransaction().commit();
            System.out.println("success");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Coord> getWeatherFromDB() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Coord>coordList=new ArrayList<>();
        try {
            session.beginTransaction();
            String sql = "select lon, lat from Coord ";
            NativeQuery query = session.createNativeQuery(sql);
            query.addScalar("lon", Long.class);
            query.addScalar("lat", Long.class);
            query.setResultListTransformer(Transformers.aliasToBean(Coord.class));
            List<Object[]> objects = query.getResultList();
            for (Object[] obj : objects) {
                Coord coord = new Coord();
                coord.setLon((double) safeToInt(obj[0]));
                coord.setLat((double) safeToInt(obj[1]));
                coordList.add(coord);
            }
            session.getTransaction().commit();
            return coordList;
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
            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null || obj1.toString().isEmpty()) {
            return defaultValue;
        }

        return obj1.toString();
    }

    public static Boolean safeToBoolean(Object obj1) {
        if (obj1 == null || obj1 instanceof Boolean) {
            return (Boolean) obj1;
        }
        return false;
    }

    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1) {
        return safeToString(obj1, "");
    }

    public static LocalDate safeToDate(Object obj) {
        if (obj != null) {
            if (obj instanceof Date) {
                return ((Date) obj).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }
        }
        return null;
    }

    public static int safeToInt(Object obj1, int defaultValue) {
        int result = defaultValue;
        if (obj1 != null) {
            try {
                result = Integer.parseInt(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    /**
     * @param obj1 Object
     * @return int
     */
    public static int safeToInt(Object obj1) {
        return safeToInt(obj1, 0);
    }
}
