package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLog";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public String mUsername;
   public String mPhotoUrl;

    TextView Username;
    ImageView PhotoUrl;
    TextView Message;

    Button signOut;
    ImageButton sendBtn;
    EditText etMessage;

    Object obj;

    private RecyclerView recyclerView;
    final List<FriendlyMessage> friendlyMessageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    FriendlyMessage messages = new FriendlyMessage();

    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;

    public FirebaseRecyclerAdapter<FriendlyMessage, MessageAdapter.MessageViewHolder> mFirebaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signOut = (Button) findViewById(R.id.btn_sign_out);
        signOut.setOnClickListener(this);

        etMessage = (EditText) findViewById(R.id.message_et);


        sendBtn = (ImageButton) findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FriendlyMessage friendlyMessage2 = new
                        FriendlyMessage(mUsername,
                        mPhotoUrl,
                        etMessage.getText().toString()
                );
                mFirebaseDatabaseReference.child("messages")
                        .push().setValue(friendlyMessage2);
                etMessage.setText("");
            }
        });

        Username = (TextView) findViewById(R.id.tv_username);
        Message = (TextView) findViewById(R.id.tv_message);
//            Date = (TextView) itemView.findViewById(R.id.tv_date);
        PhotoUrl = (ImageView) findViewById(R.id.img_photo);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {

            Toast.makeText(MainActivity.this,
                    "You have been signed in.",
                    Toast.LENGTH_SHORT)
                    .show();

            mUsername = mFirebaseUser.getDisplayName();


            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();

            }
        }

        initViews();


        mFirebaseDatabaseReference.child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

              Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Log.v(TAG,"Before" +friendlyMessageList.toString() );
                friendlyMessageList.clear();

                for (DataSnapshot child: children) {
                    FriendlyMessage messages = child.getValue(FriendlyMessage.class);





                    friendlyMessageList.add(messages);
                    initViews();


                }
                Log.v(TAG,"After" +friendlyMessageList.toString() );



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//



//        mFirebaseAdapter = new FirebaseRecyclerAdapter<FriendlyMessage,
//                MessageAdapter.MessageViewHolder>(
//
//                FriendlyMessage.class,
//                R.layout.message_layout,
//                MessageAdapter.MessageViewHolder.class,
//                mFirebaseDatabaseReference.child("messages")) {
//
//
//            //            Message view holder important!
//            @Override
//            protected void populateViewHolder(MessageAdapter.MessageViewHolder viewHolder,
//                                              FriendlyMessage friendlyMessage, int position) {
//
//
//                viewHolder.Username.setText(mUsername);
//                viewHolder.Message.setText(mPhotoUrl);
////               viewHolder.Username.setText(friendlyMessage.getUsername());
////                viewHolder.Message.setText(friendlyMessage.getMessage());
//                if (friendlyMessage.getPhotoUrl() == null)
//
//                {
//                    viewHolder.PhotoUrl
//                            .setImageDrawable(ContextCompat
//                                    .getDrawable(MainActivity.this,
//                                            R.mipmap.user));
//                } else
//
//                {
//                    Glide.with(MainActivity.this)
//                            .load(friendlyMessage.getPhotoUrl())
////                            .load(mPhotoUrl)
//                            .into(viewHolder.PhotoUrl);
//                }
//            }
//        };

//        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
//                int lastVisiblePosition =
//                        linearLayoutManager.findLastCompletelyVisibleItemPosition();
//                // If the recycler view is initially being loaded or the
//                // user is at the bottom of the list, scroll to the bottom
//                // of the list to show the newly added message.
//                if (lastVisiblePosition == -1 ||
//                        (positionStart >= (friendlyMessageCount - 1) &&
//                                lastVisiblePosition == (positionStart - 1))) {
//                    recyclerView.scrollToPosition(positionStart);
//                }
//            }
//        });




    }

    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        MessageAdapter messageAdapeter = new MessageAdapter(friendlyMessageList);
        recyclerView.setAdapter(messageAdapeter);
//        recyclerView.setAdapter(mFirebaseAdapter);


    }


    private void signOut() {
        mFirebaseUser = null;
        // Firebase sign out
        mFirebaseAuth.signOut();
        Toast.makeText(MainActivity.this,
                "You have been signed out.",
                Toast.LENGTH_SHORT)
                .show();

        startActivity(new Intent(this, SignInActivity.class));
        finish();

//        // Google sign out
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//
//                    }
//                });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_sign_out) {
            Log.v(TAG, "clicky");
            signOut();
        }
    }
}
