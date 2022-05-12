package fr.isep.bookingRoom.infrastructure.port;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public interface TranslatorAdapterPort {
    String getParametersToString(Map<String, String> params) throws UnsupportedEncodingException;

    String translate(String languageTo, String text) throws IOException;
}
