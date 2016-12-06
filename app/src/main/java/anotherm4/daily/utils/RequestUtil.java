package anotherm4.daily.utils;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import anotherm4.daily.adapter.ViewPagerAdapter;

/**
 * Created by Another.M on 2016/11/25.
 */

class RequestUtil {
    //private String url = ViewPagerAdapter.REQUEST_URL;

    String request(String strUrl, String method) throws Exception {
        HttpURLConnection conn;
        BufferedReader reader;
        String rs;
        StringBuilder sb = new StringBuilder();
        URL url = new URL(strUrl);
        conn = (HttpURLConnection) url.openConnection();
        if (method == null || method.equals("GET")) {
            conn.setRequestMethod("GET");
        } else {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
        }
        conn.setUseCaches(false);
        conn.setConnectTimeout(5000 * 3);
        conn.setReadTimeout(5000 * 3);
        conn.setInstanceFollowRedirects(false);
        conn.connect();
        InputStream is = conn.getInputStream();
        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead;
        while ((strRead = reader.readLine()) != null) {
            sb.append(strRead);
        }
        rs = sb.toString();
        reader.close();
        return rs;
    }
/*
    public void requestTest() {
        try {
            result = request(url, "GET");
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getInt("error_code") == 0) {
                Log.d("Success", String.valueOf(jsonObject.request("result")));

              Message message=new Message();
              message.what=1;

              if (Integer.valueOf(jsonObject.optJSONObject("result").optString("stat")) != 0) {
                    Log.d("data", jsonObject.optJSONObject("result").optString("data"));
                    JSONArray jsonArray = new JSONArray(jsonObject.optJSONObject("result").optString("data"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                        String title = jsonObject1.optString("title");
                        String date = jsonObject1.optString("date");
                        Log.d("test",title+date);
                    }
                } else {
                    Log.d("error", "error");
                }

            } else {
                Log.d("Error", jsonObject.request("error_code") + ":" + jsonObject.request("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
