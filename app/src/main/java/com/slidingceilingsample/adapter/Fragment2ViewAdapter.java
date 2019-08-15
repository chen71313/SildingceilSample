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
public class Fragment2ViewAdapter extends RecyclerView.Adapter<Fragment2ViewAdapter.ViewHolder> {

    private Context context;
    private List<SomeDataInfo> data;

    public Fragment2ViewAdapter(Context context, List<SomeDataInfo> data){
        this.context = context;
        this.data = data;

    }

    @Override
    public Fragment2ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_fragment2_item_layout,parent,false);
        return new Fragment2ViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Fragment2ViewAdapter.ViewHolder holder, final int position) {
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

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
