package com.example.uas_pwpb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<Student> listPersonInfo;

    public RecyclerviewAdapter(Context context, List<Student> listPersonInfo, OnUserClickListener listener) {
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    public interface OnUserClickListener{
        void onUserClick(Student currentPerson, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_layout,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Student currentPerson = listPersonInfo.get(position);
        holder.txtDate.setText(currentPerson.getDate());
        holder.txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice(currentPerson);
            }
        });
        holder.txtDeskripsi.setText(currentPerson.getJenkel());
        holder.txtDeskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice(currentPerson);
            }
        });
        holder.txtTitle.setText(currentPerson.getName());
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice(currentPerson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate;
        TextView txtTitle;
        TextView txtDeskripsi;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.tvDate);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            txtDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
        }
    }

    public void choice(final Student person) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Choose Option");
        CharSequence[] items = {"Update Data", "Hapus Data"};
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0 :
                        updateData(person);
                        break;
                    case 1 :
                        deleteData(person);
                        break;
                }
            }
        });
        alertDialog.show();
    }

    private void updateData(Student currentPerson) {
        listener.onUserClick(currentPerson,"Edit");
    }

    private void deleteData(Student currentPerson) {
        listener.onUserClick(currentPerson,"Delete");
    }
}
