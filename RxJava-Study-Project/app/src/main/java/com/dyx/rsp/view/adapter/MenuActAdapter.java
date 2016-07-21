package com.dyx.rsp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyx.rsp.R;
import com.dyx.rsp.view.ui.MenuAct;

import java.util.List;

/**
 * project name：RxJava-Study-Project
 * class describe：
 * create person：dayongxin
 * create time：16/7/21 下午11:07
 * alter person：dayongxin
 * alter time：16/7/21 下午11:07
 * alter remark：
 */
public class MenuActAdapter extends RecyclerView.Adapter<MenuActAdapter.ItemViewHolder> {
    private Context context;
    private List<String> list;

    private OnRvItemClickListener mOnRvItemClickListener;

    public interface OnRvItemClickListener {
        void onItemClick(int pos);
    }

    public void setmOnRvItemClickListener(OnRvItemClickListener mOnRvItemClickListener) {
        this.mOnRvItemClickListener = mOnRvItemClickListener;
    }

    public MenuActAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_menu_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.mTv_item.setText(list.get(position));
        if (mOnRvItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnRvItemClickListener.onItemClick(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTv_item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

}
