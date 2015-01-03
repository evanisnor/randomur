package com.ewisnor.randomur.util;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Created by evan on 2015-01-02.
 */
public class StreamHelper {

    /**
     * Get the content of an HttpResponse as an InputStream
     * @param response
     * @return
     */
    public static InputStream getResponseBodyStream(HttpResponse response) throws IOException {
        if (response.getEntity() != null) {
            InputStream inStream = response.getEntity().getContent();
            Header contentEncoding = response.getFirstHeader("Content-Encoding");

            if (contentEncoding != null
                    && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                inStream = new GZIPInputStream(inStream);
            }

            return inStream;
        }
        return null;
    }

    /**
     * Convert an InputStream to a String
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
		/*
		 * Method readLine is used to convert the InputStream to String. Iterate
		 * until the BufferedReader returns null, which means there's no more
		 * data to read. Each line will be appended to a StringBuilder and
		 * returned as String.
		 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static byte[] convertStreamToByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int bytesRead = 0;
        while((bytesRead = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }
}
