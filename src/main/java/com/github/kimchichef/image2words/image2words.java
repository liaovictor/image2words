package com.github.kimchichef.image2words;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Base64;

@AllArgsConstructor
@Getter
@Setter
class Req {
    private String url;
    private String base64;
    private String fetch4me;
    private boolean fetchLocally;
}

class Res {
    private String result;

    public String getResult() {
        return this.result;
    }
}

@Slf4j
public class image2words {
    private static String run(Req aReq) {
        Gson gson = new Gson();
        if (aReq.isFetchLocally()) {
            byte[] bytes = new byte[0];
            try {
                bytes = Jsoup.connect(aReq.getUrl()).ignoreContentType(true)
                        .execute().bodyAsBytes();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("fetching url failed: {}", e);
                return null;
            }
            String base64 = Base64.getEncoder().encodeToString(bytes);
            aReq.setBase64(base64);
            aReq.setUrl(null);
        }
        String req = gson.toJson(aReq);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=utf-8"), req);
        Request request = new Request.Builder()
                .url("https://j.pdps.cn/f/image2words")
                .post(body)
                .build();
        String res = "{}";
        try (Response response = client.newCall(request).execute()) {
            res = response.body().string();
            log.debug("response: {}", res);
//            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Res resj = gson.fromJson(res, Res.class);
        return resj.getResult();
    }

    @Deprecated
    public static String image2words(String url) {
        return fromUrl(url);
    }

    public static String fromUrl(String url) {
        Req aReq = new Req(url, null, null, false);
        return run(aReq);
    }

    public static String fromUrl(String url, Boolean fetch4me) {// not working
        Req aReq = new Req(url, null, fetch4me ? "1" : null, false);
        return run(aReq);
    }

    public static String fromUrlAndFetchLocally(String url) {

        Req aReq = new Req(url, null, null, true);
        return run(aReq);
    }

    public static String fromBase64(String base64) {
        Req aReq = new Req(null, base64, null, false);
        return run(aReq);
    }
}
