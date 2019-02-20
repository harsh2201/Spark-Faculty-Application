package com.example.minesh.navigationd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Schedule_adapter extends RecyclerView.Adapter<Schedule_adapter.FacultyViewHolder> {
    private Context mCtx;
    private List<Faculty_retrieve_class> facultyList;
    private Schedule_adapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Schedule_adapter.OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public Schedule_adapter(Context mCtx, List<Faculty_retrieve_class> facultyList) {
        this.mCtx = mCtx;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public Schedule_adapter.FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.schedule_list_lay,null);
        return new Schedule_adapter.FacultyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Schedule_adapter.FacultyViewHolder facultyViewHolder, int i) {
        Faculty_retrieve_class fac= facultyList.get(i);
        facultyViewHolder.textSlot.setText(fac.gSlot());
        facultyViewHolder.textRoom.setText(fac.gRoom());
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    class FacultyViewHolder extends RecyclerView.ViewHolder{

        TextView textSlot;
        TextView textRoom;

        public FacultyViewHolder(@NonNull View itemView, final Schedule_adapter.OnItemClickListener listener) {
            super(itemView);

            textSlot=itemView.findViewById(R.id.textviewLab);
            textRoom=itemView.findViewById(R.id.textViewRoom);

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
