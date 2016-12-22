package com.example.firebase;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Luillo on 13/12/16.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {


    String date2;

    private List<FriendlyMessage> friendlyMessageList;
    Context context;

    public MessageAdapter(List<FriendlyMessage> friendlyMessageList) {
        this.friendlyMessageList = friendlyMessageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_layout, parent, false);

        MessageViewHolder messageViewHolder = new MessageViewHolder(rowView);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        FriendlyMessage friendlyMessage = friendlyMessageList.get(position);

        holder.Username.setText(friendlyMessage.getUsername());
        holder.Message.setText(friendlyMessage.getMessage());


        getDate();
        holder.Date.setText(date2);



//        if (friendlyMessage.getPhotoUrl() == null)
//
//        {
//            holder.PhotoUrl
//                    .setImageDrawable(ContextCompat
//                            .getDrawable(context,
//                                    R.mipmap.user));
//        } else
//
//        {
//            Glide.with(context)
//                    .load(friendlyMessage.getPhotoUrl())
//                    .asGif()
////                            .load(mPhotoUrl)
//                    .into(holder.PhotoUrl);
//        }
    }

    private void getDate() {

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        String date = day + "/" + month + "/" + year;
         date2 = hour + ":" + minute;
    }

    @Override
    public int getItemCount() {
        return friendlyMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView Username;
        ImageView PhotoUrl;
        TextView Message;
        TextView Date;

        public MessageViewHolder(View itemView) {
            super(itemView);

            Username = (TextView) itemView.findViewById(R.id.tv_username);
            Message = (TextView) itemView.findViewById(R.id.tv_message);
            Date = (TextView) itemView.findViewById(R.id.tv_date);
            PhotoUrl = (ImageView) itemView.findViewById(R.id.img_photo);
        }


//        public void bindMessage(FriendlyMessage friendlyMessage) {
//
//            Username.setText(friendlyMessage.getUsername());
//            Message.setText(friendlyMessage.getMessage());
////            Date.setText(friendlyMessage.getDate());
//
//        }
    }


}
