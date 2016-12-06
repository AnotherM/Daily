package anotherm4.daily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import anotherm4.daily.R;
import anotherm4.daily.adapter.RecyclerViewAdapter;
import anotherm4.daily.bean.DataBean;
import anotherm4.daily.utils.JsonUtil;

/**
 * Created by anotherm4 on 2016/11/11.
 */

public class ViewPagerFragment extends Fragment {
    JsonUtil jsonUtil = new JsonUtil();
    List<DataBean> list=new ArrayList<>();

    /*private Handler handler;*/

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        /*
        Bundle bundle = getActivity().getIntent().getExtras();
        String author = bundle.getString("Author");
        String title = bundle.getString("Title");
        String date = bundle.getString("Date");
        String img1 = bundle.getString("Img1");
        String img2 = bundle.getString("Img2");
        String img3 = bundle.getString("Img3");
        String url = bundle.getString("url");
        */
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(true);

        list=jsonUtil.request();

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext());
        recyclerViewAdapter.setData(list);

        recyclerView.setAdapter(recyclerViewAdapter);

        return rootView;
    }
}