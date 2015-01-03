package com.ewisnor.randomur.util;

import com.ewisnor.randomur.application.RandomurLogger;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;

/**
 * HTTP helper methods for doing common things.
 *
 * Created by evan on 2015-01-02.
 */
public class HttpHelper {

    public static final int TIMEOUT_MS = 30000; //  30 second timeout

    /**
     * Create a new HTTP client with timeout parameters defined by HttpHelper
     * @return HttpClient
     */
    private static HttpClient createHttpClient() {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MS);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MS);
        return new DefaultHttpClient(httpParams);
    }

    /**
     * Send an HTTP GET request. Returns null if an error has been encountered.
     * @param request GET request
     * @return HttpResponse
     */
    private static HttpResponse get(HttpGet request) {
        HttpClient client = createHttpClient();
        HttpResponse response = null;

        try {
            response = client.execute(request);
        }
        catch (ClientProtocolException cpe) {
            RandomurLogger.error("Client Protocol Exception encountered" + cpe.getMessage());
        }
        catch (IOException ioe) {
            RandomurLogger.error("IOException encountered" + ioe.getMessage());
        }

        return response;
    }

    /**
     * Perform an HTTP get against the provided URL (with optional headers) and expect a
     * JSON string in return. Useful for hitting RESTful endpoints.
     * Overrides the "Accept" header to "application/json"
     * @param url The RESTful endpoint URL
     * @param headers Optional array of headers to add to the request
     * @return JSON string and the HTTP status code in a Pair
     * @throws IOException
     */
    public static Pair<String, Integer> GetJson(String url, Header... headers) throws IOException {
        HttpGet request = new HttpGet(url);
        for (Header h : headers) {
            request.addHeader(h);
        }
        request.setHeader("Accept", "application/json");
        HttpResponse response = get(request);
        int statusCode = response.getStatusLine().getStatusCode();
        InputStream responseStream = StreamHelper.getResponseBodyStream(response);
        String json = StreamHelper.convertStreamToString(responseStream);
        return new Pair<>(json, statusCode);
    }

    /**
     * Perform an HTTP get against the provided URL (with optional headers) and expect an
     * InputStream in return. Useful for downloading files.
     * @param url The url to download from
     * @param headers Optional array of headers to add to the request
     * @return An InputStream of bytes and the HTTP status code in a Pair
     * @throws IOException
     */
    public static Pair<InputStream, Integer> GetByteStream(String url, Header... headers) throws IOException {
        HttpGet request = new HttpGet(url);
        for (Header h : headers) {
            request.addHeader(h);
        }
        HttpResponse response = get(request);
        int statusCode = response.getStatusLine().getStatusCode();
        InputStream responseStream = StreamHelper.getResponseBodyStream(response);
        return new Pair<>(responseStream, statusCode);
    }
}
