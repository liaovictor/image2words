package com.github.kimchichef.image2words;

import static com.github.kimchichef.image2words.image2words.fromUrl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.kimchichef.image2words.image2words;
import com.google.gson.JsonSyntaxException;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.stream.Collectors;

/**
 * Unit test for simple image2words.
 */
public class image2wordsTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void testCorrectAnswer() {
        String result = "result";

        try {
            result = image2words.fromUrl("http://mmbiz.qpic.cn/mmbiz_jpg/lmj7LYw8vJMSXInnsaODzdKUeUpgwhtY2NMrtypx03kXy9iceSWPTYfOMxjY4X3kZ5Qd97O2TXMzyibsrsr8BKwA/0");
        } catch (JsonSyntaxException e) {
            System.out.println("found exception in test");
            e.printStackTrace();
        }
        System.out.println(result);
        assertTrue(result.contains("产"));
    }

    @Test
    public void testBase64() throws IOException {

        byte[] bytes = Files.readAllBytes(new File("src/test/testImg.jpg").toPath());
        System.out.println(bytes.length);
        String base64 = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);

        String result = "result";

        // this line is somehow contaminated
//         base64 = "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$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";

        try {
            result = image2words.fromBase64(base64);
        } catch (JsonSyntaxException e) {
            System.out.println("found exception in test");
            e.printStackTrace();
        }
        System.out.println(result);
        assertTrue(result.contains("CEYBLN"));
    }

    @Test
    public void testBase64WithSoup() throws IOException {
        String result = "result";

        String url = "https://images-na.ssl-images-amazon.com/captcha/cdkxpfei/Captcha_hvhaaoukna.jpg";

        byte[] bytes = Jsoup.connect(url).ignoreContentType(true)
                .execute().bodyAsBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);
        try {
            result = image2words.fromBase64(base64);
        } catch (JsonSyntaxException e) {
            System.out.println("found exception in test url");
            e.printStackTrace();
        }
        System.out.println(result);
        assertTrue(result.contains("NMCULT"));
    }

    @Test
    public void testFromUrlAndFetchLocally() {
        String result = "result";
        String url = "https://images-na.ssl-images-amazon.com/captcha/bysppkyq/Captcha_ldjhynecuc.jpg";
        result = image2words.fromUrlAndFetchLocally(url);
        System.out.println(result);
        assertTrue(result.contains("LGKPNY"));

    }

//    @Test
//    public void testFetch4me(){//TypeError: fetch is not a function
//        String result = "result";
//
//        try {
//            result = image2words.fromUrl("http://mmbiz.qpic.cn/mmbiz_jpg/lmj7LYw8vJMSXInnsaODzdKUeUpgwhtY2NMrtypx03kXy9iceSWPTYfOMxjY4X3kZ5Qd97O2TXMzyibsrsr8BKwA/0",true);
//        } catch (JsonSyntaxException e) {
//            System.out.println("found exception in test");
//            e.printStackTrace();
//        }
//        System.out.println(result);
//        assertTrue(result.contains("产"));
//    }


}
