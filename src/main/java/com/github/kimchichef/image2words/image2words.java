package com.github.kimchichef.image2words;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import okhttp3.*;

import java.io.IOException;

@AllArgsConstructor
class Req {
    private String url;
    private String base64;
    private String fetch4me;

}

class Res {
    private String result;

    public String getResult() {
        return this.result;
    }
}

//@Slf4j
public class image2words {
    private static String run(Req aReq) {
        Gson gson = new Gson();
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
        Req aReq = new Req(url, null, null);
        return run(aReq);
    }

    public static String fromUrl(String url, Boolean fetch4me) {// not working
        Req aReq = new Req(url, null, fetch4me ? "1" : null);
        return run(aReq);
    }

    public static String fromBase64(String base64) {
        Req aReq = new Req(null, base64, null);
        return run(aReq);
    }
}
