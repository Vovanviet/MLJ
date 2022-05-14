package dao;


import APIwithJson.WeatherRoot2;
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

public class WeatherRootDao {
    public void saveRootAPI(WeatherRoot2 weatherRoot){
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
//            session.save(weatherRoot);
            Query query=session.createQuery("insert into WeatherRoot2 (base,visiblity,dt,timezone,id,name,cod,dateTime)" +
                    "values(:w_base,:w_vis,:w_dt,:w_timez,:w_id,:w_name,:w_cod,:w_date)");
            query.setParameter("w_base",weatherRoot.getBase());
            query.setParameter("w_vis",weatherRoot.getVisiblity());
            query.setParameter("w_dt",weatherRoot.getDt());
            query.setParameter("w_timez",weatherRoot.getTimezone());
            query.setParameter("w_id",weatherRoot.getId());
            query.setParameter("w_name",weatherRoot.getName());
            query.setParameter("w_cod",weatherRoot.getCod());
            query.setParameter("w_date",weatherRoot.getDateTime());
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
    public List<WeatherRoot2> getWeatherFromDB() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<WeatherRoot2> weatherRoot2 = new ArrayList<>();
        try {
            session.beginTransaction();
            String sql = "select base, visiblity, dt, timezone, id, name, cod, dateTime, c_id from weatherroot";
             NativeQuery query= session.createNativeQuery(sql);
             query.addScalar("base",String.class );
             query.addScalar("visiblity", Long.class);
             query.addScalar("dt", Long.class);
             query.addScalar("timezone", Long.class);
             query.addScalar("name", String.class);
             query.addScalar("cod", Long.class);
             query.addScalar("dateTime", Date.class);
             query.addScalar("c_id", Long.class);
             query.setResultListTransformer(Transformers.aliasToBean(WeatherRoot2.class));
//            String hql="select base,visiblity,dt,timezone,id,name,cod,dateTime from WeatherRoot2";
//            weatherRoot2=    session.createQuery(hql).getResultList();
//            session.getTransaction().commit();// dòng này chỉ dùng khi insert, update, delete
            List<Object[]> resultList = query.getResultList();
            for(Object[] obj : resultList){
                WeatherRoot2 dto = new WeatherRoot2();
                dto.setBase(safeToString(obj[0]));
                dto.setVisiblity(safeToInt(obj[1]));
                dto.setDt(safeToInt(obj[2]));
                dto.setTimezone(safeToInt(obj[3]));
                dto.setId(safeToInt(obj[3]));
                dto.setName(safeToString(obj[4]));
                dto.setCod(safeToInt(obj[5]));
                dto.setDateTime(safeToDate(obj[6]));
                weatherRoot2.add(dto);
            }
            return  weatherRoot2;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return  null ;
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






//            List<WeatherRoot> weatherRootList= session.createQuery("select cd.lon,cd.lat,wt.id,wt.main,wt.description," +
//                    "wt.icon,wr.base,mn.temp,mn.feels_like,mn.temp_min,mn.temp_max," +
//                    "mn.pressure,mn.humidity,mn.sea_level,mn.grnd_level," +
//                    "wr.visiblity,wd.speed,wd.deg,wd.gust," +
//                    "cl.all,wr.dt,s.type,s.id,s.country,s.sunrise,s.sunset," +
//                    "wr.timezone,wr.id,wr.name,wr.cod from WeatherRoot2 wr \n" +
//                    "\tjoin Clouds cl on wr.c_id=cl.c_id \n" +
//                    "\tjoin Coord cd on cl.c_id=cd.c_id \n" +
//                    "\tjoin Main mn on mn.c_id=cd.c_id\n" +
//                    "    join Weather wt on mn.c_id=wt.c_id\n" +
//                    "    join Sys s on s.c_id=wt.c_id\n" +
//                    "    join Wind wd on wd.c_id=s.c_id").getResultList();