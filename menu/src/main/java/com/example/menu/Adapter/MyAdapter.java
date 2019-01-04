package com.example.menu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.menu.R;
import com.example.menu.Utils.MyItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends RecyclerView.Adapter{
    private LayoutInflater inflater;
    private ArrayList<HashMap<String ,Object>> listItem;
    private MyItemClickListener myItemClickListener;

    public MyAdapter(Context context,ArrayList<HashMap<String ,Object>> listItem){
        inflater=LayoutInflater.from(context);
        this.listItem=listItem;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Title,Text;
        private ImageView ima;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.Itemtitle);
            Text=itemView.findViewById(R.id.Itemtext);
            ima=itemView.findViewById(R.id.ItemImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(myItemClickListener!=null){
                        myItemClickListener.onItemClick(v,getPosition());
                    }
                }//监听到点击就回调MainActivity的onItemClick函数
            });
        }
        public TextView getTitle() {
            return Title;
        }
        public TextView getText() {
            return Text;
        }
        public ImageView getIma() {
            return ima;
        }
    }
    @NonNull
    @Override//定义ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.list_cell,null));
    }//在这里把ViewHolder绑定Item的布局

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh= (ViewHolder) viewHolder;
        vh.Title.setText(listItem.get(i).get("ItemTitle")+"");
        vh.Text.setText(listItem.get(i).get("ItemText")+"");
        vh.ima.setImageResource((Integer)listItem.get(i).get("ItemImage"));
    }//在这里绑定数据到ViewHolder里面

    @Override
    public int getItemCount() {
        return listItem.size();
    }//返回Item数目
    public void setOnItemClickListener(MyItemClickListener listener){
        myItemClickListener=listener;
    }//绑定MainActivity传进来的点击监听器
}
