package com.example.minesh.navigationd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Lab_info_adapter extends RecyclerView.Adapter<Lab_info_adapter.FacultyViewHolder> {
    private Context mCtx;
    private List<LabInfo_class> labInfoClassList;
    private Lab_info_adapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Lab_info_adapter.OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public Lab_info_adapter(Context mCtx, List<LabInfo_class> labInfoClassList) {
        this.mCtx = mCtx;
        this.labInfoClassList = labInfoClassList;
    }

    @NonNull
    @Override
    public Lab_info_adapter.FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.laboccupancy_list_lay,null);
        return new Lab_info_adapter.FacultyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Lab_info_adapter.FacultyViewHolder facultyViewHolder, int i) {
        LabInfo_class lab= labInfoClassList.get(i);
        facultyViewHolder.textLab.setText(lab.gLab_No());
    }

    @Override
    public int getItemCount() {
        return labInfoClassList.size();
    }

    class FacultyViewHolder extends RecyclerView.ViewHolder{

        TextView textLab;

        public FacultyViewHolder(@NonNull View itemView, final Lab_info_adapter.OnItemClickListener listener) {
            super(itemView);

            textLab =itemView.findViewById(R.id.textviewLab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
