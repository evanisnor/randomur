package com.ewisnor.randomur;

import com.ewisnor.randomur.imgur.ImgurApi;
import com.ewisnor.randomur.imgur.model.GalleryAlbum;
import com.ewisnor.randomur.imgur.util.ImageUrlHelper;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.JsonHelper;
import com.ewisnor.randomur.util.Pair;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by evan on 2015-01-03.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ImgurTest {

    @Test
    public void testImgurThumbnailUrl() throws Exception {
        String thumbnailUrl = ImageUrlHelper.CreateThumbnailUrl("https://i.imgur.com/68BTGPz.jpg");
        Assert.assertEquals("https://i.imgur.com/68BTGPzs.jpg", thumbnailUrl);
        thumbnailUrl = ImageUrlHelper.CreateThumbnailUrl("https://i.imgur.com/2534532.jpg");
        Assert.assertEquals("https://i.imgur.com/2534532s.jpg", thumbnailUrl);
    }

    @Test
    public void testJsonParse() throws Exception {
        String galleryJson = "  {\n" +
                "        \"id\": \"lDRB2\",\n" +
                "        \"title\": \"Imgur Office\",\n" +
                "        \"description\": null,\n" +
                "        \"datetime\": 1357856292,\n" +
                "        \"cover\": \"24nLu\",\n" +
                "        \"account_url\": \"Alan\",\n" +
                "        \"account_id\": 4,\n" +
                "        \"privacy\": \"public\",\n" +
                "        \"layout\": \"blog\",\n" +
                "        \"views\": 13780,\n" +
                "        \"link\": \"http://alanbox.imgur.com/a/lDRB2\",\n" +
                "        \"ups\": 1602,\n" +
                "        \"downs\": 14,\n" +
                "        \"score\": 1917,\n" +
                "        \"is_album\": true,\n" +
                "        \"vote\": null,\n" +
                "        \"comment_count\": 10,\n" +
                "        \"images_count\": 11,\n" +
                "        \"images\": [\n" +
                "            {\n" +
                "                \"id\": \"24nLu\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856352,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 855658,\n" +
                "                \"views\": 135772,\n" +
                "                \"bandwidth\": 116174397976,\n" +
                "                \"link\": \"http://i.imgur.com/24nLu.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"Ziz25\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856394,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 919391,\n" +
                "                \"views\": 135493,\n" +
                "                \"bandwidth\": 124571044763,\n" +
                "                \"link\": \"http://i.imgur.com/Ziz25.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"9tzW6\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856385,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 655028,\n" +
                "                \"views\": 135063,\n" +
                "                \"bandwidth\": 88470046764,\n" +
                "                \"link\": \"http://i.imgur.com/9tzW6.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"dFg5u\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856378,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 812738,\n" +
                "                \"views\": 134704,\n" +
                "                \"bandwidth\": 109479059552,\n" +
                "                \"link\": \"http://i.imgur.com/dFg5u.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"oknLx\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856338,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 1749,\n" +
                "                \"height\": 2332,\n" +
                "                \"size\": 717324,\n" +
                "                \"views\": 32938,\n" +
                "                \"bandwidth\": 23627217912,\n" +
                "                \"link\": \"http://i.imgur.com/oknLx.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"OL6tC\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856321,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 1443262,\n" +
                "                \"views\": 32346,\n" +
                "                \"bandwidth\": 46683752652,\n" +
                "                \"link\": \"http://i.imgur.com/OL6tC.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"cJ9cm\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856330,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 544702,\n" +
                "                \"views\": 31829,\n" +
                "                \"bandwidth\": 17337319958,\n" +
                "                \"link\": \"http://i.imgur.com/cJ9cm.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"7BtPN\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856369,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 844863,\n" +
                "                \"views\": 31257,\n" +
                "                \"bandwidth\": 26407882791,\n" +
                "                \"link\": \"http://i.imgur.com/7BtPN.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42ib8\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856424,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 2592,\n" +
                "                \"height\": 1944,\n" +
                "                \"size\": 905073,\n" +
                "                \"views\": 30945,\n" +
                "                \"bandwidth\": 28007483985,\n" +
                "                \"link\": \"http://i.imgur.com/42ib8.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"BbwIx\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856360,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 1749,\n" +
                "                \"height\": 2332,\n" +
                "                \"size\": 662413,\n" +
                "                \"views\": 30107,\n" +
                "                \"bandwidth\": 19943268191,\n" +
                "                \"link\": \"http://i.imgur.com/BbwIx.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"x7b91\",\n" +
                "                \"title\": null,\n" +
                "                \"description\": null,\n" +
                "                \"datetime\": 1357856406,\n" +
                "                \"type\": \"image/jpeg\",\n" +
                "                \"animated\": false,\n" +
                "                \"width\": 1944,\n" +
                "                \"height\": 2592,\n" +
                "                \"size\": 618567,\n" +
                "                \"views\": 29259,\n" +
                "                \"bandwidth\": 18098651853,\n" +
                "                \"link\": \"http://i.imgur.com/x7b91.jpg\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        GalleryAlbum album = JsonHelper.parseJson(galleryJson, GalleryAlbum.class);
        Assert.assertEquals("lDRB2", album.getId());
        Assert.assertEquals(11, album.getImages().length);
        Assert.assertTrue(11 == album.getImagesCount());
    }
}
