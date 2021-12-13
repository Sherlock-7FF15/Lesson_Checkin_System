package com.example.sqlnetwork.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnetwork.R;
import com.example.sqlnetwork.domain.Result;

import java.util.ArrayList;
import java.util.List;

public class GetStudentListAdapter extends RecyclerView.Adapter<GetStudentListAdapter.InnerHolder> {
    private List<Result.Student> data = new ArrayList<>();
    private GetStudentListAdapter.OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_student, parent, false);
        return new GetStudentListAdapter.InnerHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder,final int position) {
        View itemView = holder.itemView;
        TextView studentName = itemView.findViewById(R.id.itemStudentName);
        TextView studentMajor = itemView.findViewById(R.id.itemStudentMajor);
        TextView studentGrade = itemView.findViewById(R.id.itemStudentGrade);

        Result.Student student = data.get(position);
        studentName.setText(student.getName());
        studentMajor.setText(student.getMajor());
        studentGrade.setText(student.getGrade());

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


    public void setData(Result result) {
        data.clear();
        if(result.getData()!=null){
            data.addAll(result.getData());
            notifyDataSetChanged();
        }
    }

    public List<Result.Student> getData(){
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
    public  void  setOnItemClickListener(GetStudentListAdapter.OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener =onItemClickListener;
    }
}
