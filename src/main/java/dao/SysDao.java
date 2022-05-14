package dao;

import APIwithJson.Clouds;
import APIwithJson.Main;
import APIwithJson.Sys;
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
        List<Sys>sysList=new ArrayList<>();
        try {
            session.beginTransaction();
            String sql="select type,id,country,sunrise,sunset from Sys";
            NativeQuery query = session.createNativeQuery(sql);
            query.setResultListTransformer(Transformers.aliasToBean(Sys.class));
            query.addScalar("type",Long.class);
            query.addScalar("id",Long.class);
            query.addScalar("country",String.class);
            query.addScalar("sunrise",Long.class);
            query.addScalar("sunset",Long.class);
            List<Object[]>objects=query.getResultList();
            for (Object[] obj:objects) {
                Sys sys=new Sys();
                sys.setType(safeToInt(obj[0]));
                sys.setId(safeToInt(obj[1]));
                sys.setCountry(safeToString(obj[2]));
                sys.setSunrise(safeToInt(obj[3]));
                sys.setSunset(safeToInt(obj[4]));
                sysList.add(sys);
            }
            return sysList;
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
