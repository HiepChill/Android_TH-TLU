package com.hyep.baikiemtra.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hyep.baikiemtra.Listeners.OnERowClick;
import com.hyep.baikiemtra.Models.Employee;
import com.hyep.baikiemtra.R;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context con;
    private ArrayList<Employee> employeeList;
//    private OnERowClick onERowClick;

    public EmployeeAdapter(Context con, ArrayList<Employee> employeeList) {
        this.con = con;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.row_item, parent, false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(view);
        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);

        String id = employee.getId();
        String image = employee.getImage();
        String name = employee.getName();

        holder.txtEName.setText(name);
        if (image.isEmpty()) {
            holder.imgProfile.setImageResource(R.drawable.baseline_person_24);
        } else {
            holder.imgProfile.setImageURI(Uri.parse(image));
        }

//        holder.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onERowClick.onRowClick(id);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (employeeList != null) {
            return employeeList.size();
        }
        return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile;
        TextView txtEName;
        CardView container;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            txtEName = itemView.findViewById(R.id.txtEName);
            container = itemView.findViewById(R.id.container);
        }
    }
}
