package anotherm4.daily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import anotherm4.daily.R;
import anotherm4.daily.adapter.ViewPagerAdapter;
import anotherm4.daily.utils.AsyncUtil;

public class ViewPagerFragment extends Fragment {
    /*private Handler handler;*/
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
        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.pg_main);
        new AsyncUtil(getContext(), progressBar, recyclerView).execute(ViewPagerAdapter.REQUEST_URL);

        return rootView;
    }
}