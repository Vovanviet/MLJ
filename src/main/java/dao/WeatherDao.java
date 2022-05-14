package dao;

import APIwithJson.Clouds;
import APIwithJson.Weather;
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

public class WeatherDao {
    public void saveMainAPI(List<Weather> weather){
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("insert into Weather (id,main,description,icon)" +
                    "values(:w_id,:w_main,:w_desc,:w_icon)");
            query.setParameter("w_id",weather.get(0).getId());
            query.setParameter("w_main",weather.get(0).getMain());
            query.setParameter("w_desc",weather.get(0).getDescription());
            query.setParameter("w_icon",weather.get(0).getIcon());
            query.executeUpdate();
//            session.save(weather);
            session.getTransaction().commit();
            System.out.println("success");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }public List<Weather> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<Weather>weatherList=new ArrayList<>();
        try {
            session.beginTransaction();
            String sql="select id,main,description,icon from Weather ";
            NativeQuery query = session.createNativeQuery(sql);
            query.addScalar("id",Long.class);
            query.addScalar("main",String.class);
            query.addScalar("description",String.class);
            query.addScalar("icon",String.class);
            query.setResultListTransformer(Transformers.aliasToBean(Weather.class));
            List<Object[]> result=query.getResultList();
            for (Object[] obj:result) {
                Weather dto=new Weather();
                dto.setId(safeToInt(obj[0]));
                dto.setMain(safeToString(obj[1]));
                dto.setDescription(safeToString(obj[2]));
                dto.setIcon(safeToString(obj[3]));
                weatherList.add(dto);
            }
            return weatherList;
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
