package com.github.kimchichef;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;


class Req {
    private String url;

    public Req(String url) {
        this.url = url;
    }
}

class Res {
    private String result;

    public String getResult() {
        return this.result;
    }
}


public class image2words {
    public static String image2words(String q) {
        Req aReq = new Req(q);
        Gson gson = new Gson();
        String req = gson.toJson(aReq);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=utf-8"), req);
        Request request = new Request.Builder()
                .url("https://proto.col.ai/f/image2words")
                .post(body)
                .build();
        String res = "{}";
        try (Response response = client.newCall(request).execute()) {
            res = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Res resj = gson.fromJson(res, Res.class);
        return resj.getResult();
    }


}
