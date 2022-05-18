package Global;
import org.json.JSONException;
import org.json.JSONObject;
import org.python.util.PythonInterpreter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class Student<T> {
    String name="unnamed", surname ="unnamed", telegram = "0", vk = "0", group = "unnamed";
    void post() throws IOException {

        try (PythonInterpreter pyInterp = new PythonInterpreter()) {

            pyInterp.exec("import requests");

            pyInterp.exec("data = {\"FName\":\""+name+"\", \"LName\":\""+surname+"\", \"VkId\":\""+vk+"\", \"TelegrammId\":\""+telegram+"\", \"Group\":\""+group+"\"}");
            pyInterp.exec("a = requests.post(\"http://buldakovn.pythonanywhere.com/addStudent\", data)");
            //pyInterp.exec("print(a.text)");
        }
        /*

        str = new StringBuilder();
        url = new URL("https://buldakovn.pythonanywhere.com/getStudents");
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            for (String str2; (str2 = reader.readLine()) != null; ) {
                str.append(str2);
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
