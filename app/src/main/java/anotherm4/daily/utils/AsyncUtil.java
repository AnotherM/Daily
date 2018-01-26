package anotherm4.daily.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import anotherm4.daily.adapter.RecyclerViewAdapter;
import anotherm4.daily.adapter.ViewPagerAdapter;
import anotherm4.daily.bean.DataBean;

@SuppressLint("StaticFieldLeak")

public class AsyncUtil extends AsyncTask<String, Void, List<DataBean>> {
    private Context context;
    private RecyclerView recyclerView;

    public AsyncUtil(Context context, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<DataBean> doInBackground(String... strings) {
        try {
            return new JsonUtil().getJsonData(ViewPagerAdapter.REQUEST_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(List<DataBean> list) {
        super.onPostExecute(list);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context, list);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
