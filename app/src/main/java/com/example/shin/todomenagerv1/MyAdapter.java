package com.example.shin.todomenagerv1;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private ArrayList<Tasks> mTasks = new ArrayList<>();

    private RecyclerView mRecyclerView;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTask;
        public TextView mDesc;

        public MyViewHolder(View pItem) {

            super(pItem);
            mTask = pItem.findViewById(R.id.task_title);
            mDesc = pItem.findViewById(R.id.task_desc);

        }
    }


    public MyAdapter(Context context, ArrayList<Tasks> pTasks, RecyclerView pRecyclerView) {

        this.mContext = context;
        mTasks = pTasks;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from((viewGroup.getContext())).inflate(R.layout.task_layout, viewGroup, false);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    deleteRowAndTask(view);
                    return true;
                }

            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView text;
                    text = view.findViewById(R.id.task_title);

                    text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }
            });

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Tasks tasks = mTasks.get(position);
        ((MyViewHolder) holder).mTask.setText(tasks.getTask());
        ((MyViewHolder) holder).mDesc.setText(tasks.getDescription());

    }

    @Override
    public int getItemCount() {

        return mTasks.size();
    }

    private int getRowPosition(View v) {
        int position = 0;

        position = mRecyclerView.getChildAdapterPosition(v);
        if (position != 0) {
            return position;
        } else return 0;

    }

    void deleteRowAndTask(View v) {

        DatabaseHelper db = new DatabaseHelper(mContext);
        Tasks task;
try {
    int positionToDelete = getRowPosition(v);

    task = mTasks.get(positionToDelete);
    mTasks.remove(positionToDelete);

    db.deleteTask(task);

    Toast.makeText(mContext, "Task \"" + task.getTask() + "\" has been deleted", Toast.LENGTH_SHORT).show();

    notifyItemRemoved(positionToDelete);
    notifyDataSetChanged();
}catch (IndexOutOfBoundsException e){
    Toast.makeText(mContext, "cos nie tak " , Toast.LENGTH_SHORT).show();
}
    }
}































