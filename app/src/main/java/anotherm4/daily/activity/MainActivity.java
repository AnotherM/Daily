package anotherm4.daily.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import anotherm4.daily.R;
import anotherm4.daily.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics firebaseAnalytics;
    private AdView adView;
    private Boolean isShowAds = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_container);
        viewPager.setOffscreenPageLimit(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter ViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(ViewPagerAdapter);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "event_id");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "event_name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "event_type");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()/*.addTestDevice("2DD6262B2D035877E1956E7FAA2DA2CE") Add your device id here, you can find it in the logcat,or modify the ad unit id in the string.xml*/.build();
        adView.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_about) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.on_developing)
                    .setMessage(R.string.donate)
                    .setPositiveButton(R.string.alipay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://d.alipay.com/i/index.htm?b=RECEIVE_AC&u=CG7pUdmtX99kGMA+RC9Z27yceBek0RNhNasoDnOL7AQ="));
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.paypal, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.paypal.me/anotherm4"));
                            startActivity(intent);
                        }
                    })
                    .show();
            return true;
        }
        if (id == R.id.menu_hide_ads) {

            if (isShowAds) {
                adView.setVisibility(View.GONE);
                item.setTitle(R.string.show_ads);
                isShowAds = false;
            } else {
                adView.setVisibility(View.VISIBLE);
                item.setTitle(R.string.hide_ads);
                isShowAds = true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
    }
}
