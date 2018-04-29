package com.example.sagar.companyproduct.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sagar.companyproduct.R;
import com.example.sagar.companyproduct.model.TaskNo;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by SAGAR on 29-04-2018.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>
{
    private ArrayList<TaskNo> tasks;
    private Context context;
    onItemClickListener listener;
    public interface onItemClickListener
    {
        void onClick(int position);
    }
    public UserRecyclerAdapter(ArrayList<TaskNo> tasks,Context context,onItemClickListener listener)
    {
        this.tasks=tasks;
        this.context=context;
        this.listener = listener;
    }

    @Override
    public UserRecyclerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.rows_task_user,parent,false);
        UserRecyclerAdapter.UserViewHolder viewHolder=new UserRecyclerAdapter.UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final UserRecyclerAdapter.UserViewHolder holder, int position)
    {
        TaskNo taskNo =tasks.get(position);
        holder.company_name.setText(taskNo.getCompanyName());
        holder.address.setText(taskNo.getAddress());
        holder.contact.setText(taskNo.getContact());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.getAdapterPosition());

            }
        });
        if(Objects.equals(taskNo.getDate(), " "))
        {
            holder.date.setText("Please Update the Date of your Visit");
        }
        else {
            holder.date.setText(taskNo.getDate());
        }
        if(Objects.equals(taskNo.getReason(), " ")&& Objects.equals(taskNo.getStatus(), "NO"))
        {
            holder.reason.setText("Please Write the Reason");
        }
        else if(Objects.equals(taskNo.getReason(), "Yes"))
        {
            holder.reason.setText("Visit Successful");
        }
        else {
            holder.reason.setText("Update the Reason if your meeting was not Successful");
        }
        if(Objects.equals(taskNo.getStatus(), " "))
        {
            holder.status.setText("Please Update the Status After your Meeting");
        }
        else {
            holder.status.setText(taskNo.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        TextView company_name,date,reason,status,address,contact;
        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            company_name=itemView.findViewById(R.id.user_company);
            date=itemView.findViewById(R.id.user_date);
            reason=itemView.findViewById(R.id.user_reason);
            status=itemView.findViewById(R.id.user_status);
            address=itemView.findViewById(R.id.user_address);
            contact=itemView.findViewById(R.id.user_contact);
        }
    }
}
