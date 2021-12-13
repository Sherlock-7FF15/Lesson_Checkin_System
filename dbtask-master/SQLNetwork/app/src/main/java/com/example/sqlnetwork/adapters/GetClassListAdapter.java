package com.example.sqlnetwork.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sqlnetwork.R;
import com.example.sqlnetwork.domain.ClassResult;

import java.util.ArrayList;
import java.util.List;

public class GetClassListAdapter extends RecyclerView.Adapter<GetClassListAdapter.InnerHolder> {
    private List<ClassResult.Class> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_class, parent, false);
        return new InnerHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, final int position) {
        View itemView = holder.itemView;
        TextView className = itemView.findViewById(R.id.item_class);
        ClassResult.Class aClass = data.get(position);
        className.setText(aClass.getClassName());
        if( mOnItemClickListener!= null){
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ClassResult.Class> classResult) {
        data.clear();
        if(classResult !=null && classResult.size()!=0){
            data.addAll(classResult);
            notifyDataSetChanged();
        }
    }

    public List<ClassResult.Class> getData() {
        return data;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        void  onClick(  int  position);
    }
    public  void  setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener =onItemClickListener;
    }
}
