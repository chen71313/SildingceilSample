package com.slidingceilingsample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.slidingceilingsample.R;
import com.slidingceilingsample.model.SomeDataInfo;

import java.util.List;

public class Fragment1viewAdapter extends RecyclerView.Adapter<Fragment1viewAdapter.ViewHolder> {
 
    private Context context;
    private List<SomeDataInfo> data;
 
    public Fragment1viewAdapter(Context context, List<SomeDataInfo> data){
        this.context = context;
        this.data = data;
 
    }
 
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_fragment1_item_layout,parent,false);
        return new ViewHolder(view);
    }
 
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.desc.setText(data.get(position).desc);
        holder.icon.setImageResource(data.get(position).imageUrl);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
 
    }
 
    @Override
    public int getItemCount() {
        return data.size();
    }
 
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView desc;
 
        public ViewHolder(View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.desc);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}