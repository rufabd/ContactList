package edu.ktu.contactlist;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    // Initialize variable
    Activity activity;
    ArrayList<ContactModel> arrayList;

    // Create constructor
    public MainAdapter(Activity activity, ArrayList<ContactModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Initialize contact model
        ContactModel model = arrayList.get(position);

        // Set name
        holder.tvName.setText(model.getName());
        // Set number
        holder.tvNumber.setText(model.getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView tvName, tvNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign varibale
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    String numberClicked = arrayList.get(position).getNumber();
                    Toast.makeText(activity, numberClicked, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(numberClicked));

//                    final String userPhoneNumber = arrayList.get(position).getNumber();
//
//                    new AlertDialog.Builder(activity)
//                            .setTitle("Call a Contact")
//                            .setMessage("Are you sure you want to call:" + userPhoneNumber)
//                            .setPositiveButton("Call", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    callUser(userPhoneNumber);
//                                }
//                            })
//                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Do nothing.
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
                }

//                public void callUser(String phoneNum) {
//
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//
//                    callIntent.setData(Uri.parse("tel:" + phoneNum + ""));
//
//                    if (ActivityCompat.checkSelfPermission(activity,
//                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        return;
//                    }
//                    startActivity(callIntent);
//                }
            });
        }
    }
}
