package com.example.cs435_hw05;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class TaskAdapter  extends RecyclerView.Adapter{

    TaskAdapterListener taskAdapterListener;

    public interface TaskAdapterListener{
        public void clickDetail(int position);
    }

    public TaskAdapter(TaskAdapterListener taskAdapterListener){
        this.taskAdapterListener = taskAdapterListener;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewDesc;
        TextView textViewPrio;
        LinearLayout layoutTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewPrio = itemView.findViewById(R.id.textViewPrio);
            layoutTask = (LinearLayout)itemView.findViewById(R.id.linearLayoutTask);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    taskAdapterListener.clickDetail(position);
                }
            });
        }

        public void updateTextViews(Task task){
            textViewName.setText(task.taskName);
            textViewDesc.setText(task.description);
            textViewPrio.setText(task.priority);
            if (task.priority.equals("1")) {
                layoutTask.setBackgroundColor(Color.RED);
            }else if(task.priority.equals("2")){
                layoutTask.setBackgroundColor(Color.rgb(255, 165, 0));
            }else if(task.priority.equals("3")){
                layoutTask.setBackgroundColor(Color.YELLOW);
            }else if(task.priority.equals("4")){
                layoutTask.setBackgroundColor(Color.GREEN);
            }else{
                layoutTask.setBackgroundColor(Color.BLUE);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_layout,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskViewHolder taskViewHolder = (TaskViewHolder) holder;
        Task task = Model.tasks.get(position);
        taskViewHolder.updateTextViews(task);
    }

    @Override
    public int getItemCount() {
        return Model.tasks.size();
    }
}
