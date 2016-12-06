package anotherm4.daily.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import anotherm4.daily.adapter.ViewPagerAdapter;
import anotherm4.daily.bean.DataBean;

/**
 * Created by Another.M on 2016/12/1.
 */

public class JsonUtil {
    private final String requestUrl = ViewPagerAdapter.REQUEST_URL;
    private final RequestUtil requestUtil = new RequestUtil();

    public List<DataBean> request() {
        List<DataBean> dataBeanList = new ArrayList<>();
        try {
            String result = requestUtil.request(requestUrl, "GET");
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = new JSONArray(jsonObject.optJSONObject("result").optString("data"));
            for (int i = 0; i < jsonArray.length(); i++) {
                DataBean dataBean = new DataBean();
                JSONObject object = jsonArray.optJSONObject(i);
                String title = object.optString("title");
                String date = object.optString("date");
                String authorName = object.optString("author_name");
                String thumbnailPic1 = object.optString("thumbnail_pic_s");
                String thumbnailPic2 = object.optString("thumbnail_pic_s02");
                String thumbnailPic3 = object.optString("thumbnail_pic_s03");
                String url = object.optString("url");
                dataBean.setTitle(title);
                dataBean.setDate(date);
                dataBean.setAuthor(authorName);
                dataBean.setImg1Url(thumbnailPic1);
                dataBean.setImg2Url(thumbnailPic2);
                dataBean.setImg3Url(thumbnailPic3);
                dataBean.setWebUrl(url);
                dataBeanList.add(dataBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBeanList;
    }
}
