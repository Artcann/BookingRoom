package fr.isep.bookingRoom.infrastructure.adapter;

import fr.isep.bookingRoom.infrastructure.port.TranslatorAdapterPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Component
public class TranslatorAdapter implements TranslatorAdapterPort {
    @Value("${DEEPL_API_URL}")
    private String deeplUrl;
    @Value("${DEEPL_AUTHENTIFICATION}")
    private String auth_key;

    public String translate(String languageTo, String text) throws IOException{
        URL url = new URL(deeplUrl);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", text);
        parameters.put("target_lang", languageTo);
        parameters.put("auth_key", auth_key);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        DataOutputStream translateOutput = new DataOutputStream(connection.getOutputStream());
        translateOutput.writeBytes(getParametersToString(parameters));
        translateOutput.flush();
        translateOutput.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String httpResponse = "";
        StringBuilder content = new StringBuilder();

        while((httpResponse = in.readLine()) != null) {
            content.append(httpResponse);
        }
        in.close();
        connection.disconnect();

        content.delete(0, content.indexOf("text")+7);
        content.delete(content.length()-4, content.length());
        String traduction = content.toString();
        return traduction;
    }

    public String getParametersToString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for(Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }
}
