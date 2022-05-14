package dao;

import APIwithJson.Clouds;
import APIwithJson.Main;
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
        List<Main> mainList=new ArrayList<>();
        try {
            session.beginTransaction();
            String sql="select temp,feels_like,temp_min,temp_max,pressure,humidity,sea_level,grnd_level from Main ";
            NativeQuery query = session.createNativeQuery(sql);
            query.addScalar("temp",Long.class);
            query.addScalar("feels_like",Long.class);
            query.addScalar("temp_min",Long.class);
            query.addScalar("temp_max",Long.class);
            query.addScalar("pressure",Long.class);
            query.addScalar("humidity",Long.class);
            query.addScalar("sea_level",Long.class);
            query.addScalar("grnd_level",Long.class);
            query.setResultListTransformer(Transformers.aliasToBean(Main.class));
            List<Object[]>result=query.getResultList();
            for (Object[] obj:result) {
                Main main=new Main();
                main.setTemp((double) safeToInt(obj[1]));
                main.setFeelsLike((double) safeToInt(obj[2]));
                main.setTempMin((double) safeToInt(obj[3]));
                main.setTempMax((double) safeToInt(obj[3]));
                main.setPressure(safeToInt(obj[3]));
                main.setHumidity( safeToInt(obj[3]));
                main.setSeaLevel( safeToInt(obj[3]));
                main.setGrndLevel( safeToInt(obj[3]));
                mainList.add(main);
            }
            return mainList;
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
