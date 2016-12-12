package anotherm4.daily.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import anotherm4.daily.adapter.RecyclerViewAdapter;
import anotherm4.daily.adapter.ViewPagerAdapter;
import anotherm4.daily.bean.DataBean;

public class AsyncUtil extends AsyncTask<String, Void, List<DataBean>> {
    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public AsyncUtil(Context context, ProgressBar progressBar, RecyclerView recyclerView) {
        this.context = context;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
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
        progressBar.setVisibility(View.GONE);
    }
}
