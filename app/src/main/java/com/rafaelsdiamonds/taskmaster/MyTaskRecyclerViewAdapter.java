package com.rafaelsdiamonds.taskmaster;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListTasksQuery;
import com.example.taskmaster.R;
import com.rafaelsdiamonds.taskmaster.TaskFragment.OnListFragmentInteractionListener;
import com.rafaelsdiamonds.taskmaster.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final String TAG = "rvrv";
    private List<ListTasksQuery.Item> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<ListTasksQuery.Item> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).title());
        holder.mBodyView.setText(mValues.get(position).body());
        holder.mStateView.setText(mValues.get(position).state());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = v.getContext();
                Intent i = new Intent(context,TaskDetail.class).putExtra("Task",mValues.get(position).title())
                        .putExtra("TaskDetails",mValues.get(position).body());
                if(mValues.get(position).team().name() != null){
                    i.putExtra("TaskTeam",mValues.get(position).team().name()).putExtra("TaskImg",mValues.get(position).image());
                }
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mBodyView;
        public final TextView mStateView;
        public final TextView mTeamView;

        public ListTasksQuery.Item mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mBodyView = (TextView) view.findViewById(R.id.body);
            mStateView = (TextView) view.findViewById(R.id.state);
            mTeamView = (TextView) view.findViewById(R.id.teamForThisTask);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mBodyView.getText() + "'";
        }
    }
}