package anotherm4.daily.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import anotherm4.daily.R;
import anotherm4.daily.bean.DataBean;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public InputStream imgUrl;
    private Context context;
    private List<DataBean> dataBeanList;

    public RecyclerViewAdapter(Context context, List<DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }


    public void setData(List<DataBean> list) {
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
                    Picasso.with(context).load(dataBeanList.get(position).getImg1Url()).into(viewHolder.ivImg);
                } else if (dataBeanList.get(position).getImg2Url() != null) {
                    Picasso.with(context).load(dataBeanList.get(position).getImg2Url()).into(viewHolder.ivImg);
                } else if (dataBeanList.get(position).getImg3Url() != null) {
                    Picasso.with(context).load(dataBeanList.get(position).getImg3Url()).into(viewHolder.ivImg);
                }
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
        ImageView ivMenu;

        ViewHolder(View itemView) {
            super(itemView);
            ivMenu = (ImageView) itemView.findViewById(R.id.data_menu);
            tvAuthor = (TextView) itemView.findViewById(R.id.data_author);
            tvTitle = (TextView) itemView.findViewById(R.id.data_title);
            tvDate = (TextView) itemView.findViewById(R.id.data_date);
            // tvContent = (TextView) itemView.findViewById(R.id.data_content);待开发
            ivImg = (ImageView) itemView.findViewById(R.id.data_image);
            ivMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(context).setMessage(R.string.on_developing).show();
                }
            });
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
