package Global;
import java.io.IOException;
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
        URL url = new URL("https://buldakovn.pythonanywhere.com/addStudent");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String temp = "{\"FName\":"+name+",\"SName\":"+surname+",\"VkId\":"+vk+",\"TelegrammId\":"+telegram+",\"Group\":"+group+"}";
        byte[] out = temp.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.connect();
        try(OutputStream os = connection.getOutputStream()) {
            os.write(out);
        }

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
