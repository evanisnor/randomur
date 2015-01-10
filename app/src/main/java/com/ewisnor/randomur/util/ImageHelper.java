package com.ewisnor.randomur.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Helper methods for handling BitmapFactory operations.
 *
 * Created by evan on 15-01-07.
 */
public class ImageHelper {
    /**
     * Maximum dimensions (width and height) for a Bitmap on Android
     */
    public static final Integer MAX_IMAGE_BOUNDS = 2048;

    /**
     * Determine the width and height of the image without actually decoding the bitmap.
     * @param imageBytes Bitmap image bytes
     * @return A Pair instance. First: width. Second: height.
     */
    public static Pair<Integer, Integer> getImageBounds(byte[] imageBytes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeStream(stream, null, options);
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
        return new Pair<>(options.outWidth, options.outHeight);
    }

    /**
     * Scale down an image by a factor of 4 (perhaps this could be more intelligent in the future).
     * @param imageBytes Bitmap image bytes
     * @return A decoded, scaled bitmap
     */
    public static Bitmap getScaledDownImage(byte[] imageBytes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
    }
}
