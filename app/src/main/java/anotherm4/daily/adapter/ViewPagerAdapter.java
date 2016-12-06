package anotherm4.daily.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import anotherm4.daily.R;
import anotherm4.daily.fragment.ViewPagerFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private static final String APP_KEY = "db9f990cff415702e640d2dfd845f758";
    public static String REQUEST_URL;

    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=top&key=" + APP_KEY;
                break;
            case 1:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=shehui&key=" + APP_KEY;
                break;
            case 2:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=guonei&key=" + APP_KEY;
                break;
            case 3:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=guoji&key=" + APP_KEY;
                break;
            case 4:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=yule&key=" + APP_KEY;
                break;
            case 5:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=tiyu&key=" + APP_KEY;
                break;
            case 6:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=junshi&key=" + APP_KEY;
                break;
            case 7:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=keji&key=" + APP_KEY;
                break;
            case 8:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=caijing&key=" + APP_KEY;
                break;
            case 9:
                REQUEST_URL = "http://v.juhe.cn/toutiao/index?type=shishang&key=" + APP_KEY;
                break;
        }
        return ViewPagerFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.category_top);
            case 1:
                return context.getString(R.string.category_shehui);
            case 2:
                return context.getString(R.string.category_guonei);
            case 3:
                return context.getString(R.string.category_guoji);
            case 4:
                return context.getString(R.string.category_yule);
            case 5:
                return context.getString(R.string.category_tiyu);
            case 6:
                return context.getString(R.string.category_junshi);
            case 7:
                return context.getString(R.string.category_keji);
            case 8:
                return context.getString(R.string.category_caijing);
            case 9:
                return context.getString(R.string.category_shishang);
            default:
                return null;
        }
    }
}
