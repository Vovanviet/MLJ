package IO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Write {
    public static void main(String[] args) throws IOException {
        WriteObjects();
    }
    public static void WriteFile() throws IOException {
        Map<String,Object>map=new TreeMap<>();
        map.put("id",1);
        map.put("name","viet");
        FileWriter write=new FileWriter("test.json");
        new Gson().toJson(map,write);
        write.close();
        System.out.println("success");
    }
    public static void WriteObjects() throws IOException {
        List<Account>accounts= Arrays.asList(
                new Account(1,"Viet"),
                new Account(2,"Nam")
        );
        Gson gson=new GsonBuilder().setPrettyPrinting().create();

        Writer writer= Files.newBufferedWriter(Paths.get("data.json"), StandardCharsets.UTF_8);
        gson.toJson(accounts,writer);
        writer.close();
        System.out.println("success");

    }


}
