package APIwithJson;

import com.google.gson.Gson;
import dao.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class WeatherApi
{
    private String keys;
    private String city;
    private String country;
    Gson gson=new Gson();

    public WeatherApi(String keys, String city, String country) {
        this.keys = keys;
        this.city = city;
        this.country = country;
    }
    String gerJsonApi(String link) throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        URL url=new URL(link);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
    public String getLink(){
        String link="https://api.openweathermap.org/data/2.5/weather?q="+country+"&appid="+keys+"&units=metric";
        return link;
    }
    public WeatherRoot getWeatherData() throws IOException {
        WeatherRoot weatherRoot=gson.fromJson(gerJsonApi(getLink()),WeatherRoot.class);
        return weatherRoot;
    }
    public void writeToDB() throws IOException {
        WeatherApi weatherApi=new WeatherApi("a3928551797abf83d8b51bab7365a594","hanoi","vn");
        Clouds clouds=weatherApi.getWeatherData().getClouds();
        Coord coord=weatherApi.getWeatherData().getCoord();
        Main main=weatherApi.getWeatherData().getMain();
        Sys sys=weatherApi.getWeatherData().getSys();
        List<Weather> weather= weatherApi.getWeatherData().getWeather();
        Wind wind=weatherApi.getWeatherData().getWind();
        String base=weatherApi.getWeatherData().getBase();
        Integer visibility=weatherApi.getWeatherData().getVisibility();
        Integer dt=weatherApi.getWeatherData().getDt();
        Integer timezone=weatherApi.getWeatherData().getTimezone();
        Integer idRoot=weatherApi.getWeatherData().getId();
        String nameRoot=weatherApi.getWeatherData().getName();
        Integer cod=weatherApi.getWeatherData().getCod();
        LocalDate dateTime=java.time.LocalDate.now();
        WeatherRoot2 weatherRoot2=new WeatherRoot2(base,visibility ,dt,timezone ,idRoot,nameRoot,cod,dateTime);



        CloudsDao cloudsDao=new CloudsDao();
        cloudsDao.saveCloudsAPI(clouds);

        CoordDao coordDao=new CoordDao();
        coordDao.saveCoordAPI(coord);

        MainDao mainDao=new MainDao();
        mainDao.saveWeatherAPI(main);

        SysDao sysDao=new SysDao();
        sysDao.saveMainAPI(sys);

        WeatherDao weatherDao=new WeatherDao();
        weatherDao.saveMainAPI(weather);

        WindDao windDao=new WindDao();
        windDao.saveMainAPI(wind);
        WeatherRootDao weatherRootDao=new WeatherRootDao();
        weatherRootDao.saveRootAPI(weatherRoot2);

    }

    public static void main(String[] args) throws IOException {
        WeatherApi weatherApi=new WeatherApi("a3928551797abf83d8b51bab7365a594","hanoi","vn");
//        weatherApi.writeToDB();

        /*Get data WeatherRoot from DB*/
        WeatherRootDao weatherRootDao=new WeatherRootDao();
        List<WeatherRoot2> weatherRoot= weatherRootDao.getWeatherFromDB();
//        System.out.println(weatherRootDao.getWeatherFromDB().get(0));

        /*Get data Clouds from DB*/
            CloudsDao cloudsDao=new CloudsDao();
        List<Clouds> cloudsList=cloudsDao.getWeatherFromDB();

////        /*Get data Clouds from DB*/
            MainDao mainDao=new MainDao();
            List<Main>mainList=mainDao.getWeatherFromDB();
//        System.out.println(mainList.get(mainList.size()-1));

        /*Get data Weather from DB*/
            WeatherDao weatherDao=new WeatherDao();
            List<Weather> weatherList=weatherDao.getWeatherFromDB();

        /*Get data Coord from DB*/
        CoordDao coordDao=new CoordDao();
        List<Coord> coordList=coordDao.getWeatherFromDB();

        /*Get data Wind from DB*/
        WindDao windDao =new WindDao();
        List<Wind> winds=windDao.getWeatherFromDB();

        /*Get data Sys from DB*/
        SysDao sysDao=new SysDao();
        List<Sys> sysList=sysDao.getWeatherFromDB();


        System.out.println("Coord:"+coordList.get(coordList.size()-1)+"\nWeather:"+weatherList.get(weatherList.size()-1)+
                "\nBase:"+weatherRoot.get(weatherRoot.size()-1).getBase()+
                "\nMain:"+mainList.get(mainList.size()-1)+"\nVisiblity:"+
                weatherRoot.get(weatherRoot.size()-1).getVisiblity()+"\nWind:"+
                winds.get(winds.size()-1)+"\nClouds:"+cloudsList.get(cloudsList.size()-1)+
                "\ndt:"+weatherRoot.get(weatherRoot.size()-1).getDt()+"\nSys:"+
                sysList.get(sysList.size()-1)+"\nTimezone:"+
                weatherRoot.get(weatherRoot.size()-1).getTimezone()+"\nid:"+
                weatherRoot.get(weatherRoot.size()-1).getId()+"\nname:"+
                weatherRoot.get(weatherRoot.size()-1).getName()+"\ncod"+
                weatherRoot.get(weatherRoot.size()-1).getCod()+"\ndate:"+
                weatherRoot.get(weatherRoot.size()-1).getDateTime());
    }
}



//        System.out.println(weatherApi.getWeatherData());
//        System.out.println(weatherApi.getWeatherData().getCoord());
//        System.out.println(weatherApi.getWeatherData().getWeather());
//        System.out.println(weatherApi.getWeatherData().getBase());
//        System.out.println(weatherApi.getWeatherData().getMain());
//
//        System.out.println(weatherApi.getWeatherData().getVisibility());
//        System.out.println(weatherApi.getWeatherData().getWind());
//        System.out.println(weatherApi.getWeatherData().getClouds());
//        System.out.println(weatherApi.getWeatherData().getDt());
//        System.out.println(weatherApi.getWeatherData().getSys());
//        System.out.println(weatherApi.getWeatherData().getTimezone());
//        System.out.println(weatherApi.getWeatherData().getId());
//        System.out.println(weatherApi.getWeatherData().getName());
//        System.out.println(weatherApi.getWeatherData().getCod());