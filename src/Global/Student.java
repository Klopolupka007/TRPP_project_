package Global;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Student<T> {
    String name="", surname ="", telegram = "", vk = "", group = "";
    void post() throws IOException {
        //Добавляем на сервер


        /*
        URL url = new URL("https://buldakovn.pythonanywhere.com/addStudent");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);


        Map<String, String> postargs = new HashMap<>();
        postargs.put("{", "");
        postargs.put("FName:", name + ",");
        postargs.put("LName:", surname + ",");
        postargs.put("VkId:", vk + ",");
        postargs.put("TelegrammId:", telegram + ",");
        postargs.put("Group:", group + "}");


        //String temp = "{\"FName\":"+name+",\"LName\":"+surname+",\"VkId\":"+vk+",\"TelegrammId\":"+telegram+",\"Group\":"+group+"}";
        byte[] out = postargs.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.addRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setConnectTimeout(200);
        connection.setReadTimeout(200);
        connection.connect();

        try {
            OutputStream os = connection.getOutputStream();
            os.write(out);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        InputStreamReader isR = new InputStreamReader(connection.getInputStream());
        BufferedReader bfr = new BufferedReader(isR);
        StringBuilder str = new StringBuilder();
        String line;
        while ((line=bfr.readLine())!=null){
            str.append(line);
        }
        System.out.println(str);

        str = new StringBuilder();
        url = new URL("https://buldakovn.pythonanywhere.com/getStudents");
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            for (String str1; (str1 = reader.readLine())!= null;){
                str.append(str1);
            }
        }
        System.out.println(str);
        */
    }

    void add(int i, String temp){
        switch (i) {
            case 0:{
                name = temp;
                break;
            }
            case 1:{
                surname = temp;
                break;
            }
            case 2:{
                telegram = temp;
                break;
            }
            case 3:{
                vk = temp;
                break;
            }
            case 4:{
                group = temp;
                break;
            }
        }
    }
}
