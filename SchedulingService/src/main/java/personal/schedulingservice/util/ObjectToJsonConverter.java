package personal.schedulingservice.util;

import com.google.gson.Gson;

public class ObjectToJsonConverter {

    private static final Gson gson = new Gson();
    public static String convertObjectToJson(Object object){
        return gson.toJson(object);
    }
}
