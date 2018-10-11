package org.mistu.android.androidlab.screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mistu.android.androidlab.R;
import org.mistu.android.androidlab.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private List<User> userList;

    public UserListAdapter() {
        userList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.tvMobile.setText(userList.get(position).getMobile());
        myViewHolder.tvAddress.setText(userList.get(position).getAddress());
        myViewHolder.tvName.setText(userList.get(position).getName());
    }

    public void resetUserList(List<User> userList) {
        this.userList.clear();
        if (userList != null) {
            this.userList.addAll(userList);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAddress;
        TextView tvMobile;

        MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            tvAddress = view.findViewById(R.id.address);
            tvMobile = view.findViewById(R.id.mobile);
        }
    }
}
