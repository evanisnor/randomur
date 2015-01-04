package com.ewisnor.randomur.imgur;

import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.imgur.model.BasicImages;
import com.ewisnor.randomur.imgur.model.GalleryAlbum;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.JsonHelper;
import com.ewisnor.randomur.util.Pair;

import org.apache.http.message.BasicHeader;

import java.io.IOException;

/**
 * Exposing Imgur API endpoints
 *
 * Created by evan on 2015-01-02.
 */
public class ImgurApi {
    private static String CLIENT_ID = "99f2f0ab1191caa";

    /**
     * Get a list of random images from the Imgur API
     * @param page Page number (60 results at a time)
     * @return A collection of metadata about random images
     * @throws IOException
     */
    public static BasicImages getRandomImageMeta(Integer page) throws IOException {
        Pair<String, Integer> response = HttpHelper.GetJson("https://api.imgur.com/3/gallery/random/random/"
                        + page.toString(),
                new BasicHeader("Authorization", "Client-ID " + CLIENT_ID));

        return JsonHelper.parseJson(response.getFirst(), BasicImages.class);
    }

}
