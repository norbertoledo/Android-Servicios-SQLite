package com.norbertoledo.pac_desarrollo_m08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class UserListItem extends RecyclerView.Adapter<UserListItem.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<User> data;

    public UserListItem(Context context, ArrayList<User> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public UserListItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = inflater.inflate(R.layout.userlist_item, parent);
        //return new ViewHolder(view);

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String fullname = data.get(position).getName()+" "+data.get(position).getSurname();
        holder.fullNameItem.setText(fullname);
        String phone = data.get(position).getPhone();
        holder.phoneItem.setText(phone);
        String gender = data.get(position).getGender();

        if(gender.equals("M")){
            holder.imageItem.setImageResource(R.drawable.user_male_icon);
        }else{
            holder.imageItem.setImageResource(R.drawable.user_female_icon);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageItem;
        TextView fullNameItem, phoneItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullNameItem = itemView.findViewById(R.id.fullNameItem);
            phoneItem = itemView.findViewById(R.id.phoneItem);
            imageItem = itemView.findViewById(R.id.imageItem);
        }
    }
}
