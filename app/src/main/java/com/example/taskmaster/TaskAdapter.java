package com.example.taskmaster;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> {
    List<TaskModel> allTasks = new ArrayList<TaskModel>();
    private OnTasksListener mOnTasksListener;


    public TaskAdapter(List<TaskModel> allTasks, OnTasksListener mOnTasksListener) {
        this.allTasks = allTasks;
        this.mOnTasksListener = mOnTasksListener;
    }



    public static class TasksViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TaskModel task;
        public View itemView;
        public OnTasksListener mOnTasksListener;

        public TasksViewHolder(@NonNull View itemView,OnTasksListener mOnTasksListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mOnTasksListener = mOnTasksListener;
            this.itemView = itemView;
        }

        @Override
        public void onClick(View v) {
            mOnTasksListener.onTaskClick(getAdapterPosition(),task);
        }
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank,parent,false);
        TasksViewHolder tsksViewHolder = new TasksViewHolder(view,mOnTasksListener);
        return tsksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.task = allTasks.get(position);
        TextView title = holder.itemView.findViewById(R.id.mTitle);
        TextView body = holder.itemView.findViewById(R.id.mbody);
        TextView state = holder.itemView.findViewById(R.id.mState);

        title.setText(holder.task.title);
        body.setText(holder.task.body);
        state.setText(holder.task.state);


    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }
    public interface OnTasksListener{
        void onTaskClick(int position,TaskModel task);
    }

}