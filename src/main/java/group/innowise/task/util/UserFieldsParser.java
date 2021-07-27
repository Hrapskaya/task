package group.innowise.task.util;
import java.util.*;


public class UserFieldsParser {

    public static final String DATA_SPLITTER = ",";
    public static final String PARAMETER_SPLITTER = "=";

    public static Map<String, String> parseParameters(String line){
        Map<String, String> parameters = new HashMap<>();
        String[] data = line.trim().split(DATA_SPLITTER);
        for (String element: data){
            String[] parameter = element.split(PARAMETER_SPLITTER);
            String key = parameter[0].trim();
            String value = "";
            if(parameter.length > 1){
                value =  parameter[1].trim();
            }
            parameters.put(key, value);
        }
        return parameters;
    }

}
