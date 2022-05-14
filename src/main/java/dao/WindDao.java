package dao;

import APIwithJson.Clouds;
import APIwithJson.Main;
import APIwithJson.Wind;
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
        List<Wind> windList=new ArrayList<>();
        try {
            session.beginTransaction();
            String sql="select speed,deg,gust from Wind ";
            NativeQuery query = session.createNativeQuery(sql);
            query.addScalar("speed",Long.class);
            query.addScalar("deg",Long.class);
            query.addScalar("gust",Long.class);
            query.setResultListTransformer(Transformers.aliasToBean(Wind.class));
            List<Object[]>result=query.getResultList();
            for (Object[] obj:result) {
                Wind wind=new Wind();
                wind.setSpeed((double) safeToInt(obj[0]));
                wind.setDeg(safeToInt(obj[1]));
                wind.setGust((double) safeToInt(obj[2]));
                windList.add(wind);
            }
            return windList;
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
