package com.example.sagar.companyproduct.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sagar.companyproduct.R;
import com.example.sagar.companyproduct.model.TaskNo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by SAGAR on 29-04-2018.
 */

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.AdminViewHolder>
{
    private ArrayList<TaskNo> tasks;
    private Context context;

    public AdminRecyclerAdapter(ArrayList<TaskNo> tasks,Context context)
    {
        this.tasks=tasks;
        this.context=context;
    }

    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.rows_task,parent,false);
        AdminViewHolder viewHolder=new AdminViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdminViewHolder holder, int position)
    {
        TaskNo taskNo =tasks.get(position);
        holder.company_name.setText(taskNo.getCompanyName());
        holder.address.setText(taskNo.getAddress());
        holder.name.setText(taskNo.getName());
        holder.contact.setText(taskNo.getContact());
        if(Objects.equals(taskNo.getDate(), " "))
        {
            holder.date.setText("Date Not Updated by User");
        }
        else {
            holder.date.setText(taskNo.getDate());
        }
        if(Objects.equals(taskNo.getReason(), " ")&& Objects.equals(taskNo.getStatus(), "NO"))
        {
            holder.reason.setText("Reason Not Updated by User");
        }
        else if(Objects.equals(taskNo.getReason(), "Yes"))
        {
            holder.reason.setText("Reason Not Requaired User has Visited the Company");
        }
        else {
            holder.reason.setText("Reason Not Updated by User");
        }
        if(Objects.equals(taskNo.getStatus(), " "))
        {
            holder.status.setText("Status Not Updated by User");
        }
        else {
            holder.status.setText(taskNo.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        TextView name;
        TextView company_name,date,reason,status,address,contact;
        public AdminViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            name=itemView.findViewById(R.id.admin_name);
            company_name=itemView.findViewById(R.id.admin_company);
            date=itemView.findViewById(R.id.admin_date);
            reason=itemView.findViewById(R.id.admin_reason);
            status=itemView.findViewById(R.id.admin_status);
            address=itemView.findViewById(R.id.admin_address);
            contact=itemView.findViewById(R.id.admin_contact);
        }
    }
}
