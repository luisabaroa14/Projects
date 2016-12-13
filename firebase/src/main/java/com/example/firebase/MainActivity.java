package com.example.firebase;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLog";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    String mUsername;
    String mPhotoUrl;
    Button signOut;

    // Firebase instance variables
    private FirebaseDatabase database;
    private DatabaseReference mFirebaseDatabaseReference;
//    public FirebaseRecyclerAdapter<FriendlyMessage, MessageViewHolder> mFirebaseAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signOut= (Button) findViewById(R.id.btn_sign_out);
        signOut.setOnClickListener(this);

        ProgressBar mProgressBar = new ProgressBar(this);
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

       database = FirebaseDatabase.getInstance();
        mFirebaseDatabaseReference = database.getReference();


        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            Log.i(TAG, mUsername);

            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
                Log.i(TAG, mPhotoUrl);
            }
        }


//        mFirebaseAdapter = new FirebaseRecyclerAdapter<FriendlyMessage,
//                MessageViewHolder>(
//
//                FriendlyMessage.class,
//                R.layout.item_message,
//                MessageViewHolder.class,
//                mFirebaseDatabaseReference.child(MESSAGES_CHILD)) {
//
//            @Override
//            protected void populateViewHolder(MessageViewHolder viewHolder,
//                                              FriendlyMessage friendlyMessage, int position) {
//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
//                viewHolder.messageTextView.setText(friendlyMessage.getText());
//                viewHolder.messengerTextView.setText(friendlyMessage.getName());
//                if (friendlyMessage.getPhotoUrl() == null) {
//                    viewHolder.messengerImageView
//                            .setImageDrawable(ContextCompat
//                                    .getDrawable(MainActivity.this,
//                                            R.drawable.ic_account_circle_black_36dp));
//                } else {
//                    Glide.with(MainActivity.this)
//                            .load(friendlyMessage.getPhotoUrl())
//                            .into(viewHolder.messengerImageView);
//                }
//            }
//        };

    }










    private void signOut() {
        mFirebaseUser = null;
        // Firebase sign out
        mFirebaseAuth.signOut();

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
        if (view.getId() == R.id.btn_sign_out){
            Log.v(TAG, "clicky");
            signOut();
        }
    }
}
