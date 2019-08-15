package com.slidingceilingsample.adapter;

import android.content.Context;
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

/**
 * @author: chenpengpeng
 * @date: 19-8-15
 * @desc:
 */
public class Fragment3ViewAdapter extends RecyclerView.Adapter<Fragment3ViewAdapter.ViewHolder> {

    private Context context;
    private List<SomeDataInfo> data;

    public Fragment3ViewAdapter(Context context, List<SomeDataInfo> data){
        this.context = context;
        this.data = data;

    }

    @Override
    public Fragment3ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_fragment3_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Fragment3ViewAdapter.ViewHolder holder, final int position) {
        holder.desc.setText(data.get(position).desc);
        holder.title.setText(data.get(position).title);

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
        private TextView title;
        private TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.desc);
            title = itemView.findViewById(R.id.title);
        }
    }
}

