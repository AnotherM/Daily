package anotherm4.daily.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import anotherm4.daily.R;
import anotherm4.daily.bean.DataBean;

/**
 * Created by anotherm4 on 2016/11/11.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DataBean> dataBeanList;
    private InputStream imgUrl;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        dataBeanList=new ArrayList<>();
    }

    public void setData(List<DataBean>list){
        dataBeanList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvTitle.setText(dataBeanList.get(position).getTitle());
            //viewHolder.tvContent.setText(dataBeanList.request(position));待开发
            viewHolder.tvAuthor.setText(dataBeanList.get(position).getAuthor());
            viewHolder.tvDate.setText(dataBeanList.get(position).getDate());
            try {

                if (dataBeanList.get(position).getImg1Url() != null) {
                    imgUrl = (InputStream) new URL(dataBeanList.get(position).getImg1Url()).getContent();
                } else if (dataBeanList.get(position).getImg2Url() != null) {
                    imgUrl = (InputStream) new URL(dataBeanList.get(position).getImg2Url()).getContent();
                } else if (dataBeanList.get(position).getImg3Url() != null) {
                    imgUrl = (InputStream) new URL(dataBeanList.get(position).getImg3Url()).getContent();
                }
                Drawable img = Drawable.createFromStream(imgUrl, "Img");
                viewHolder.ivImg.setImageDrawable(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor;
        TextView tvTitle;
        TextView tvDate;
        //TextView tvContent;
        ImageView ivImg;

        ViewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.data_author);
            tvTitle = (TextView) itemView.findViewById(R.id.data_title);
            tvDate = (TextView) itemView.findViewById(R.id.data_date);
            // tvContent = (TextView) itemView.findViewById(R.id.data_content);待开发
            ivImg = (ImageView) itemView.findViewById(R.id.data_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(dataBeanList.get(getAdapterPosition()).getWebUrl()));
                    /*
                    Bundle bundle = new Bundle();
                    bundle.putString("Author", dataBeanList.request(getAdapterPosition()).getAuthor());
                    bundle.putString("Title", dataBeanList.request(getAdapterPosition()).getTitle());
                    intent.putExtra("Content",dataBeanList.request(getAdapterPosition()))待开发
                    bundle.putString("Date", dataBeanList.request(getAdapterPosition()).getDate());
                    bundle.putString("Img1", dataBeanList.request(getAdapterPosition()).getImg1Url());
                    bundle.putString("Img2", dataBeanList.request(getAdapterPosition()).getImg2Url());
                    bundle.putString("Img3", dataBeanList.request(getAdapterPosition()).getImg3Url());
                    bundle.putString("url", dataBeanList.request(getAdapterPosition()).getWebUrl());
                    intent.putExtras(bundle);
                    */
                    context.startActivity(intent);
                }
            });
        }
    }
}
