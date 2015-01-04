package com.ewisnor.randomur;

import com.ewisnor.randomur.imgur.model.BasicImages;
import com.ewisnor.randomur.imgur.model.GalleryAlbum;
import com.ewisnor.randomur.imgur.util.ImageUrlHelper;
import com.ewisnor.randomur.util.JsonHelper;

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

    @Test
    public void testActualJsonData() {
        String json = "{\"data\":[{\"id\":\"KaWFX\",\"title\":\"One-star Yelp Reviews of NHL Arenas\",\"description\":null,\"datetime\":1418686460,\"cover\":\"iwtTSnv\",\"cover_width\":1280,\"cover_height\":720,\"account_url\":null,\"account_id\":null,\"privacy\":\"public\",\"layout\":\"blog\",\"views\":424235,\"link\":\"http:\\/\\/imgur.com\\/a\\/KaWFX\",\"ups\":3317,\"downs\":110,\"score\":3513,\"is_album\":true,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"hockey\",\"comment_count\":427,\"images_count\":25},{\"id\":\"PXRIn\",\"title\":\"Drew Brees... He Waits.\",\"description\":null,\"datetime\":1312070286,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1024,\"height\":2304,\"size\":363710,\"views\":172958,\"bandwidth\":62906554180,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/PXRIn.jpg\",\"comment_count\":24,\"account_id\":null,\"ups\":196,\"downs\":18,\"score\":264,\"is_album\":false},{\"id\":\"yGXo5nQ\",\"title\":\"2nd year on here. Annual post of the same picture.\",\"description\":null,\"datetime\":1412915967,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":590,\"height\":7765,\"size\":474362,\"views\":615120,\"bandwidth\":291789553440,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"IAMPANDAHEARMYROAR\",\"link\":\"http:\\/\\/i.imgur.com\\/yGXo5nQ.jpg\",\"comment_count\":417,\"account_id\":1700427,\"ups\":11942,\"downs\":300,\"score\":11949,\"is_album\":false},{\"id\":\"rQnfbEX\",\"title\":\"Hellen Keller meeting Charlie Chaplin\",\"description\":null,\"datetime\":1380982320,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":700,\"height\":860,\"size\":135212,\"views\":992746,\"bandwidth\":134231172152,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/rQnfbEX.jpg\",\"comment_count\":143,\"account_id\":null,\"ups\":3464,\"downs\":37,\"score\":4340,\"is_album\":false},{\"id\":\"T1cXi3F\",\"title\":\"That gap tho...\",\"description\":null,\"datetime\":1393580922,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":600,\"height\":800,\"size\":112505,\"views\":283945,\"bandwidth\":31945232225,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"datgap\",\"account_url\":\"iceboxo\",\"link\":\"http:\\/\\/i.imgur.com\\/T1cXi3F.jpg\",\"comment_count\":288,\"account_id\":2534171,\"ups\":2672,\"downs\":541,\"score\":2272,\"is_album\":false},{\"id\":\"nn4X0K6\",\"title\":\"ESPN is asking the right questions.\",\"description\":null,\"datetime\":1383834528,\"type\":\"image\\/png\",\"animated\":false,\"width\":890,\"height\":347,\"size\":77368,\"views\":784940,\"bandwidth\":60729237920,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/nn4X0K6.png\",\"comment_count\":1462,\"account_id\":null,\"ups\":3248,\"downs\":56,\"score\":3903,\"is_album\":false},{\"id\":\"VXMk8\",\"title\":\"The first thing you need to know about beavers\",\"description\":null,\"datetime\":1341076815,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":820,\"height\":610,\"size\":78143,\"views\":347103,\"bandwidth\":27123669729,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/VXMk8.jpg\",\"comment_count\":74,\"account_id\":null,\"ups\":472,\"downs\":4,\"score\":641,\"is_album\":false},{\"id\":\"fXGA0\",\"title\":\"Well played Bill Gates...\",\"description\":null,\"datetime\":1349589823,\"type\":\"image\\/png\",\"animated\":false,\"width\":372,\"height\":720,\"size\":373456,\"views\":709310,\"bandwidth\":264896075360,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/fXGA0.png\",\"comment_count\":168,\"account_id\":null,\"ups\":1636,\"downs\":18,\"score\":1972,\"is_album\":false},{\"id\":\"TFXpOZQ\",\"title\":\"Why yes beer cap, that is a very good point.\",\"description\":null,\"datetime\":1377437198,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1024,\"height\":1024,\"size\":148414,\"views\":1207824,\"bandwidth\":179257991136,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/TFXpOZQ.jpg\",\"comment_count\":99,\"account_id\":null,\"ups\":3601,\"downs\":43,\"score\":4475,\"is_album\":false},{\"id\":\"XCs7V\",\"title\":\"Friend took this while shopping in Japan\",\"description\":null,\"datetime\":1352715855,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":720,\"height\":960,\"size\":77726,\"views\":888651,\"bandwidth\":69071287626,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/XCs7V.jpg\",\"comment_count\":110,\"account_id\":null,\"ups\":921,\"downs\":19,\"score\":1647,\"is_album\":false},{\"id\":\"yqwXU\",\"title\":\"Enhance!\",\"description\":null,\"datetime\":1339044581,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":3207,\"height\":845,\"size\":285141,\"views\":479978,\"bandwidth\":136861406898,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/yqwXU.jpg\",\"comment_count\":38,\"account_id\":null,\"ups\":556,\"downs\":15,\"score\":778,\"is_album\":false},{\"id\":\"bnHXC3b\",\"title\":\"Calmer than a monk on morphine.....\",\"description\":null,\"datetime\":1385820186,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":630,\"height\":701,\"size\":86082,\"views\":273704,\"bandwidth\":23560987728,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"Bingbongbang\",\"link\":\"http:\\/\\/i.imgur.com\\/bnHXC3b.jpg\",\"comment_count\":241,\"account_id\":8156227,\"ups\":8854,\"downs\":145,\"score\":8845,\"is_album\":false},{\"id\":\"JXy8FOo\",\"title\":\"Blind kid vs 50 Cent\",\"description\":null,\"datetime\":1401321474,\"type\":\"image\\/gif\",\"animated\":true,\"width\":628,\"height\":351,\"size\":9571549,\"views\":2022408,\"bandwidth\":19357577269992,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"gifs\",\"account_url\":\"undercovergiraffe\",\"gifv\":\"http:\\/\\/i.imgur.com\\/JXy8FOo.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/JXy8FOo.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/JXy8FOo.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/JXy8FOo.gif\",\"looping\":true,\"comment_count\":517,\"account_id\":1373582,\"ups\":9186,\"downs\":80,\"score\":10420,\"is_album\":false},{\"id\":\"pi57OXw\",\"title\":\"Very clear water.\",\"description\":null,\"datetime\":1401143876,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":500,\"height\":667,\"size\":179520,\"views\":2054209,\"bandwidth\":368771599680,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/pi57OXw.jpg\",\"comment_count\":608,\"account_id\":null,\"ups\":12613,\"downs\":87,\"score\":13875,\"is_album\":false},{\"id\":\"4fnXN4R\",\"title\":\"Do you know what they call The Hunger Games in Paris?\",\"description\":null,\"datetime\":1389071748,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":853,\"height\":480,\"size\":47105,\"views\":523963,\"bandwidth\":24681277115,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/4fnXN4R.jpg\",\"comment_count\":253,\"account_id\":null,\"ups\":2703,\"downs\":153,\"score\":3118,\"is_album\":false},{\"id\":\"Tkh5yWX\",\"title\":\"Well a squirrel got into my apartment. I didn't think rodents would be a problem with THREE cats!\",\"description\":null,\"datetime\":1403722322,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":768,\"height\":1024,\"size\":220749,\"views\":598254,\"bandwidth\":132063972246,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"aww\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/Tkh5yWX.jpg\",\"comment_count\":191,\"account_id\":null,\"ups\":3455,\"downs\":99,\"score\":3656,\"is_album\":false},{\"id\":\"WXHaT\",\"title\":\"Kitten on bed\",\"description\":null,\"datetime\":1319464916,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1680,\"height\":1050,\"size\":279210,\"views\":120503,\"bandwidth\":33645642630,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"aww\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/WXHaT.jpg\",\"comment_count\":19,\"account_id\":null,\"ups\":481,\"downs\":17,\"score\":524,\"is_album\":false},{\"id\":\"0wwXXJJ\",\"title\":\"The real discrepancy in The Big Bang Theory\",\"description\":null,\"datetime\":1364699863,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":500,\"height\":444,\"size\":38729,\"views\":1274154,\"bandwidth\":49346710266,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/0wwXXJJ.jpg\",\"comment_count\":686,\"account_id\":null,\"ups\":2980,\"downs\":111,\"score\":3901,\"is_album\":false},{\"id\":\"yEbOecX\",\"title\":\"This is the shower I live with.\",\"description\":null,\"datetime\":1415711402,\"type\":\"image\\/png\",\"animated\":false,\"width\":800,\"height\":600,\"size\":55978,\"views\":1633645,\"bandwidth\":91448179810,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/yEbOecX.png\",\"comment_count\":428,\"account_id\":null,\"ups\":8268,\"downs\":408,\"score\":9976,\"is_album\":false},{\"id\":\"A9HwfVX\",\"title\":\"Googled \\\"how to please Imgur\\\" and this is what I got so here you go, you little shits.\",\"description\":null,\"datetime\":1383441036,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":625,\"height\":446,\"size\":102677,\"views\":220053,\"bandwidth\":22594381881,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":\"avatarphil\",\"link\":\"http:\\/\\/i.imgur.com\\/A9HwfVX.jpg\",\"comment_count\":205,\"account_id\":1876804,\"ups\":7610,\"downs\":156,\"score\":7564,\"is_album\":false},{\"id\":\"MMXsn\",\"title\":\"Space saving furniture\",\"description\":null,\"datetime\":1414284856,\"cover\":\"kTQ01kp\",\"cover_width\":454,\"cover_height\":750,\"account_url\":\"sqwigle\",\"account_id\":1434833,\"privacy\":\"hidden\",\"layout\":\"blog\",\"views\":516490,\"link\":\"http:\\/\\/imgur.com\\/a\\/MMXsn\",\"ups\":12213,\"downs\":98,\"score\":12115,\"is_album\":true,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"OldSchoolCool\",\"comment_count\":435,\"images_count\":10},{\"id\":\"sXHQoad\",\"title\":\"My Wife's Latest Cross Stitch Project is Complete! So True....\",\"description\":null,\"datetime\":1377916135,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":302,\"height\":403,\"size\":29216,\"views\":873127,\"bandwidth\":25509278432,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":\"marcdisa234\",\"link\":\"http:\\/\\/i.imgur.com\\/sXHQoad.jpg\",\"comment_count\":120,\"account_id\":1308810,\"ups\":3649,\"downs\":53,\"score\":4346,\"is_album\":false},{\"id\":\"XJpKz\",\"title\":\"The longest fooseball table I've ever seen. - Heineken factory, Amsterdam\",\"description\":null,\"datetime\":1352798662,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":537,\"height\":720,\"size\":102516,\"views\":371065,\"bandwidth\":38040099540,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/XJpKz.jpg\",\"comment_count\":64,\"account_id\":null,\"ups\":571,\"downs\":6,\"score\":1051,\"is_album\":false},{\"id\":\"dXxSC\",\"title\":\"Mace Cop' identified. Anthony V. Bologna\",\"description\":null,\"datetime\":1317093911,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":640,\"height\":425,\"size\":83524,\"views\":143926,\"bandwidth\":12021275224,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/dXxSC.jpg\",\"comment_count\":93,\"account_id\":null,\"ups\":315,\"downs\":19,\"score\":367,\"is_album\":false},{\"id\":\"XSSuc6z\",\"title\":\"a spiderweb froze on my trucks side mirror\",\"description\":null,\"datetime\":1412860314,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":900,\"height\":1200,\"size\":278926,\"views\":1301709,\"bandwidth\":363080484534,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"mildlyinteresting\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/XSSuc6z.jpg\",\"comment_count\":80,\"account_id\":null,\"ups\":2050,\"downs\":68,\"score\":2633,\"is_album\":false},{\"id\":\"FXMTjy7\",\"title\":\"Well. There goes my childhood.\",\"description\":null,\"datetime\":1373710327,\"type\":\"image\\/gif\",\"animated\":false,\"width\":576,\"height\":4986,\"size\":573846,\"views\":59180,\"bandwidth\":33960206280,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"tjackson80\",\"link\":\"http:\\/\\/i.imgur.com\\/FXMTjy7.gif\",\"comment_count\":63,\"account_id\":968246,\"ups\":3058,\"downs\":29,\"score\":3058,\"is_album\":false},{\"id\":\"4X8GFKh\",\"title\":\"Blow gently on screen\",\"description\":null,\"datetime\":1405057386,\"type\":\"image\\/gif\",\"animated\":true,\"width\":500,\"height\":227,\"size\":2048877,\"views\":291946,\"bandwidth\":598161444642,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"ChemicalXXX\",\"gifv\":\"http:\\/\\/i.imgur.com\\/4X8GFKh.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/4X8GFKh.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/4X8GFKh.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/4X8GFKh.gif\",\"looping\":true,\"comment_count\":344,\"account_id\":9817673,\"ups\":6779,\"downs\":245,\"score\":6679,\"is_album\":false},{\"id\":\"X0KK1Tp\",\"title\":\"Wanna take a ride to karma town?\",\"description\":null,\"datetime\":1387838600,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":2021,\"height\":1961,\"size\":433292,\"views\":292741,\"bandwidth\":126842333372,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/X0KK1Tp.jpg\",\"comment_count\":99,\"account_id\":null,\"ups\":2350,\"downs\":101,\"score\":2813,\"is_album\":false},{\"id\":\"7NUXJ\",\"title\":\"Cleaning my Ferrari \",\"description\":null,\"datetime\":1355157445,\"type\":\"image\\/gif\",\"animated\":true,\"width\":300,\"height\":192,\"size\":1858432,\"views\":301612,\"bandwidth\":560525392384,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"woahdude\",\"account_url\":null,\"gifv\":\"http:\\/\\/i.imgur.com\\/7NUXJ.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/7NUXJ.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/7NUXJ.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/7NUXJ.gif\",\"looping\":true,\"comment_count\":50,\"account_id\":null,\"ups\":956,\"downs\":17,\"score\":1397,\"is_album\":false},{\"id\":\"5XEw6dk\",\"title\":\"Every year, the same thing for orange.\",\"description\":null,\"datetime\":1389212717,\"type\":\"image\\/png\",\"animated\":false,\"width\":500,\"height\":500,\"size\":158046,\"views\":457511,\"bandwidth\":72307783506,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/5XEw6dk.png\",\"comment_count\":630,\"account_id\":null,\"ups\":3725,\"downs\":47,\"score\":4231,\"is_album\":false},{\"id\":\"jnw5SoX\",\"title\":\"I don\\u00b4t know what is all the fuss about Ubisoft and Unity? The game runs fine for me.\",\"description\":null,\"datetime\":1419334851,\"type\":\"image\\/png\",\"animated\":false,\"width\":1360,\"height\":617,\"size\":905752,\"views\":983870,\"bandwidth\":891142220240,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pcmasterrace\",\"account_url\":\"SimplyMike\",\"link\":\"http:\\/\\/i.imgur.com\\/jnw5SoX.png\",\"comment_count\":204,\"account_id\":3549030,\"ups\":3104,\"downs\":191,\"score\":3953,\"is_album\":false},{\"id\":\"LXhz0hu\",\"title\":\"I knew I forgot something outside last night....FML.\",\"description\":null,\"datetime\":1361283914,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":900,\"height\":1200,\"size\":276668,\"views\":920574,\"bandwidth\":254693367432,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/LXhz0hu.jpg\",\"comment_count\":237,\"account_id\":null,\"ups\":1750,\"downs\":36,\"score\":2513,\"is_album\":false},{\"id\":\"AbQIX\",\"title\":\"Every time\",\"description\":null,\"datetime\":1358814968,\"cover\":\"qTNG4Tr\",\"cover_width\":245,\"cover_height\":170,\"account_url\":\"Minisene\",\"account_id\":1704319,\"privacy\":\"public\",\"layout\":\"blog\",\"views\":24539,\"link\":\"http:\\/\\/imgur.com\\/a\\/AbQIX\",\"ups\":3637,\"downs\":63,\"score\":3574,\"is_album\":true,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"mindcrack\",\"comment_count\":211,\"images_count\":7},{\"id\":\"iX06Xvw\",\"title\":\"26.2 miles\",\"description\":null,\"datetime\":1401465818,\"type\":\"image\\/png\",\"animated\":false,\"width\":542,\"height\":639,\"size\":430290,\"views\":612157,\"bandwidth\":263405035530,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"standupshots\",\"account_url\":\"sugarkeenan\",\"link\":\"http:\\/\\/i.imgur.com\\/iX06Xvw.png\",\"comment_count\":219,\"account_id\":11840239,\"ups\":3312,\"downs\":210,\"score\":4058,\"is_album\":false},{\"id\":\"XEOIyls\",\"title\":\"Well Imgur, I just found out I'm pregnant! And this was one of the first things that came to my mind\",\"description\":null,\"datetime\":1386879102,\"type\":\"image\\/png\",\"animated\":false,\"width\":480,\"height\":480,\"size\":90498,\"views\":214115,\"bandwidth\":19376979270,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"lookinforcutekittypictures\",\"link\":\"http:\\/\\/i.imgur.com\\/XEOIyls.png\",\"comment_count\":835,\"account_id\":996568,\"ups\":7105,\"downs\":132,\"score\":7079,\"is_album\":false},{\"id\":\"FXeDDgw\",\"title\":\"Me whenever I see an 'Award winning food' or 'exercise' post\",\"description\":null,\"datetime\":1391953298,\"type\":\"image\\/gif\",\"animated\":true,\"width\":490,\"height\":278,\"size\":959132,\"views\":115381,\"bandwidth\":110665609292,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"ProphetOfPhil\",\"gifv\":\"http:\\/\\/i.imgur.com\\/FXeDDgw.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/FXeDDgw.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/FXeDDgw.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/FXeDDgw.gif\",\"looping\":true,\"comment_count\":100,\"account_id\":8459885,\"ups\":4350,\"downs\":118,\"score\":4289,\"is_album\":false},{\"id\":\"X5uxu\",\"title\":\"My friend's professor and TA in class today\",\"description\":null,\"datetime\":1351616032,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1280,\"height\":960,\"size\":247700,\"views\":988068,\"bandwidth\":244744443600,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/X5uxu.jpg\",\"comment_count\":99,\"account_id\":null,\"ups\":1015,\"downs\":18,\"score\":1801,\"is_album\":false},{\"id\":\"wJandXB\",\"title\":\"When you have to watch the baby and shovel snow.\",\"description\":null,\"datetime\":1391271491,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":604,\"height\":453,\"size\":38553,\"views\":917222,\"bandwidth\":35361659766,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"aww\",\"account_url\":\"bigwipeouts\",\"link\":\"http:\\/\\/i.imgur.com\\/wJandXB.jpg\",\"comment_count\":148,\"account_id\":6312527,\"ups\":4348,\"downs\":51,\"score\":5386,\"is_album\":false},{\"id\":\"bd4RXff\",\"title\":\"Toilet Paper Rolls + Blower + Paint Roller = Hilarious\",\"description\":null,\"datetime\":1360944855,\"type\":\"image\\/gif\",\"animated\":false,\"width\":607,\"height\":341,\"size\":10095363,\"views\":372091,\"bandwidth\":3756393714033,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"PeopleBeingJerks\",\"account_url\":\"chemistrydoc\",\"link\":\"http:\\/\\/i.imgur.com\\/bd4RXff.gif\",\"comment_count\":149,\"account_id\":1107901,\"ups\":2135,\"downs\":38,\"score\":2283,\"is_album\":false},{\"id\":\"XbOIEDE\",\"title\":\"Whoop de whoop\",\"description\":null,\"datetime\":1389037870,\"type\":\"image\\/png\",\"animated\":false,\"width\":500,\"height\":128,\"size\":47207,\"views\":267471,\"bandwidth\":12626503497,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"BooooYouWhore\",\"link\":\"http:\\/\\/i.imgur.com\\/XbOIEDE.png\",\"comment_count\":273,\"account_id\":6309311,\"ups\":9484,\"downs\":153,\"score\":9464,\"is_album\":false},{\"id\":\"aOXpy\",\"title\":\"Light play\",\"description\":null,\"datetime\":1309116140,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":500,\"height\":334,\"size\":120475,\"views\":329029,\"bandwidth\":39639768775,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/aOXpy.jpg\",\"comment_count\":56,\"account_id\":null,\"ups\":448,\"downs\":7,\"score\":605,\"is_album\":false},{\"id\":\"hkLeX\",\"title\":\"How to properly hold a cat\",\"description\":null,\"datetime\":1335083703,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":2448,\"height\":3264,\"size\":679589,\"views\":1356230,\"bandwidth\":921678989470,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/hkLeX.jpg\",\"comment_count\":108,\"account_id\":null,\"ups\":1157,\"downs\":18,\"score\":1817,\"is_album\":false},{\"id\":\"TLMEBXY\",\"title\":\"MRW my mom calls me downstairs by my full name.\",\"description\":null,\"datetime\":1385310757,\"type\":\"image\\/gif\",\"animated\":true,\"width\":500,\"height\":208,\"size\":1012925,\"views\":456490,\"bandwidth\":462390133250,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"reactiongifs\",\"account_url\":\"justanormalperson\",\"gifv\":\"http:\\/\\/i.imgur.com\\/TLMEBXY.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/TLMEBXY.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/TLMEBXY.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/TLMEBXY.gif\",\"looping\":true,\"comment_count\":268,\"account_id\":5702339,\"ups\":12560,\"downs\":85,\"score\":12703,\"is_album\":false},{\"id\":\"TgpCXl2\",\"title\":\"Favorite King of The Hill moment\",\"description\":null,\"datetime\":1404212076,\"type\":\"image\\/png\",\"animated\":false,\"width\":451,\"height\":668,\"size\":546752,\"views\":1558815,\"bandwidth\":852285218880,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":\"conradb\",\"link\":\"http:\\/\\/i.imgur.com\\/TgpCXl2.png\",\"comment_count\":247,\"account_id\":12640039,\"ups\":6624,\"downs\":166,\"score\":7238,\"is_album\":false},{\"id\":\"UXalA\",\"title\":\"I was getting bored in class so drew this. No biggie.\",\"description\":null,\"datetime\":1321981507,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":3504,\"height\":2550,\"size\":197142,\"views\":279424,\"bandwidth\":55086206208,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":\"iloveanimals\",\"link\":\"http:\\/\\/i.imgur.com\\/UXalA.jpg\",\"comment_count\":15,\"account_id\":525826,\"ups\":214,\"downs\":13,\"score\":340,\"is_album\":false},{\"id\":\"m44fXAC\",\"title\":\"I'm now a proud owner of this shower curtain\",\"description\":null,\"datetime\":1397600660,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":550,\"height\":550,\"size\":65145,\"views\":860875,\"bandwidth\":56081701875,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/m44fXAC.jpg\",\"comment_count\":262,\"account_id\":null,\"ups\":7531,\"downs\":128,\"score\":8181,\"is_album\":false},{\"id\":\"Xhxmv\",\"title\":\"So I forgot the password to my LouisCK.net account. Self-esteem lowered.\",\"description\":null,\"datetime\":1336824820,\"type\":\"image\\/png\",\"animated\":false,\"width\":724,\"height\":495,\"size\":123002,\"views\":451934,\"bandwidth\":55588785868,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/Xhxmv.png\",\"comment_count\":42,\"account_id\":null,\"ups\":876,\"downs\":15,\"score\":1086,\"is_album\":false},{\"id\":\"SIX7u5z\",\"title\":\"And suddenly, Wolf!\",\"description\":null,\"datetime\":1391605244,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1641,\"height\":1363,\"size\":262432,\"views\":493879,\"bandwidth\":129609653728,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/SIX7u5z.jpg\",\"comment_count\":428,\"account_id\":null,\"ups\":4323,\"downs\":73,\"score\":4798,\"is_album\":false},{\"id\":\"g6XIP2j\",\"title\":\"everyone's googling their usernames and I'm just like...\",\"description\":null,\"datetime\":1370492584,\"type\":\"image\\/gif\",\"animated\":true,\"width\":500,\"height\":281,\"size\":316472,\"views\":158479,\"bandwidth\":50154166088,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":null,\"account_url\":\"yourfatuglywhoremom\",\"gifv\":\"http:\\/\\/i.imgur.com\\/g6XIP2j.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/g6XIP2j.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/g6XIP2j.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/g6XIP2j.gif\",\"looping\":true,\"comment_count\":259,\"account_id\":1273751,\"ups\":2767,\"downs\":37,\"score\":2809,\"is_album\":false},{\"id\":\"XGZnJ\",\"title\":\"I just realized you could do this in Win 7.  Now I know how I am going to be trolling my co-workers\",\"description\":null,\"datetime\":1302304803,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1263,\"height\":1001,\"size\":150030,\"views\":132408,\"bandwidth\":19865172240,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/XGZnJ.jpg\",\"comment_count\":15,\"account_id\":null,\"ups\":203,\"downs\":9,\"score\":260,\"is_album\":false},{\"id\":\"nX8HTxz\",\"title\":\"Kitten-arms-pushed\",\"description\":null,\"datetime\":1374149749,\"type\":\"image\\/gif\",\"animated\":true,\"width\":324,\"height\":213,\"size\":2080710,\"views\":153368,\"bandwidth\":319114331280,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"aww\",\"account_url\":null,\"gifv\":\"http:\\/\\/i.imgur.com\\/nX8HTxz.gifv\",\"webm\":\"http:\\/\\/i.imgur.com\\/nX8HTxz.webm\",\"mp4\":\"http:\\/\\/i.imgur.com\\/nX8HTxz.mp4\",\"link\":\"http:\\/\\/i.imgur.com\\/nX8HTxz.gif\",\"looping\":true,\"comment_count\":41,\"account_id\":null,\"ups\":2346,\"downs\":46,\"score\":2844,\"is_album\":false},{\"id\":\"hSI1DXg\",\"title\":\"Thanksgiving with my Mexican inlaws, this is my whole day so far.\",\"description\":null,\"datetime\":1417121156,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":500,\"height\":263,\"size\":37967,\"views\":878620,\"bandwidth\":33358565540,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/hSI1DXg.jpg\",\"comment_count\":308,\"account_id\":null,\"ups\":5651,\"downs\":110,\"score\":6952,\"is_album\":false},{\"id\":\"9IZXdvs\",\"title\":\"Here's Twix. He puts up with a lot from the kids. Merry Christmas!\",\"description\":null,\"datetime\":1419289460,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1200,\"height\":900,\"size\":340820,\"views\":2059918,\"bandwidth\":702061252760,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"aww\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/9IZXdvs.jpg\",\"comment_count\":123,\"account_id\":null,\"ups\":6064,\"downs\":178,\"score\":6916,\"is_album\":false},{\"id\":\"yIXSHGA\",\"title\":\"Yes... yes they did\",\"description\":null,\"datetime\":1403618119,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":424,\"height\":249,\"size\":18567,\"views\":978570,\"bandwidth\":18169109190,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":\"jaydude025\",\"link\":\"http:\\/\\/i.imgur.com\\/yIXSHGA.jpg\",\"comment_count\":1203,\"account_id\":2713351,\"ups\":4451,\"downs\":532,\"score\":4409,\"is_album\":false},{\"id\":\"X44YdBC\",\"title\":\"\\\"By the time a man realizes....\\\" Charles Wadsworth \",\"description\":null,\"datetime\":1400440293,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1024,\"height\":768,\"size\":91377,\"views\":304808,\"bandwidth\":27852440616,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"QuotesPorn\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/X44YdBC.jpg\",\"comment_count\":211,\"account_id\":null,\"ups\":5718,\"downs\":184,\"score\":5993,\"is_album\":false},{\"id\":\"yXT4Eiu\",\"title\":\"A 75-foot-long pool winds its way along the lower level of this house in Columbus, Ohio (580x712)\",\"description\":null,\"datetime\":1407689948,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":580,\"height\":712,\"size\":201061,\"views\":675639,\"bandwidth\":135844652979,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"RoomPorn\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/yXT4Eiu.jpg\",\"comment_count\":403,\"account_id\":null,\"ups\":4197,\"downs\":59,\"score\":5038,\"is_album\":false},{\"id\":\"X6OvL\",\"title\":\"I saw a squirrel in a tree, I grabbed my camera and this is what I got.\",\"description\":null,\"datetime\":1315179969,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":1000,\"height\":964,\"size\":343352,\"views\":160940,\"bandwidth\":55259070880,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pics\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/X6OvL.jpg\",\"comment_count\":40,\"account_id\":null,\"ups\":372,\"downs\":9,\"score\":443,\"is_album\":false},{\"id\":\"mx9mXxX\",\"title\":\"I'm pretty sure everyone here would press the button.\",\"description\":null,\"datetime\":1378582384,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":960,\"height\":698,\"size\":101911,\"views\":317838,\"bandwidth\":32391188418,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"pokemon\",\"account_url\":\"Tokimori\",\"link\":\"http:\\/\\/i.imgur.com\\/mx9mXxX.jpg\",\"comment_count\":164,\"account_id\":218303,\"ups\":1871,\"downs\":97,\"score\":2289,\"is_album\":false},{\"id\":\"Xrir7\",\"title\":\"\\u00e0\\u00b2\\u00a0_\\u00e0\\u00b2\\u00a0\",\"description\":null,\"datetime\":1320679805,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":409,\"height\":810,\"size\":118533,\"views\":371145,\"bandwidth\":43992930285,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"funny\",\"account_url\":\"f3tish\",\"link\":\"http:\\/\\/i.imgur.com\\/Xrir7.jpg\",\"comment_count\":65,\"account_id\":519855,\"ups\":398,\"downs\":23,\"score\":560,\"is_album\":false},{\"id\":\"bzFwmXu\",\"title\":\"Priorities.\",\"description\":null,\"datetime\":1402270900,\"type\":\"image\\/jpeg\",\"animated\":false,\"width\":500,\"height\":500,\"size\":70291,\"views\":1160876,\"bandwidth\":81599134916,\"vote\":null,\"favorite\":false,\"nsfw\":false,\"section\":\"GetMotivated\",\"account_url\":null,\"link\":\"http:\\/\\/i.imgur.com\\/bzFwmXu.jpg\",\"comment_count\":322,\"account_id\":null,\"ups\":4051,\"downs\":139,\"score\":4493,\"is_album\":false}],\"success\":true,\"status\":200}";
        BasicImages album = JsonHelper.parseJson(json, BasicImages.class);
        Assert.assertEquals(60, album.getData().length);
    }
}
