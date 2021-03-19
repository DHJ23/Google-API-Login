package com.dhj.google_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Index extends AppCompatActivity {
TextView t1,t2,t3;
Button b1;
ImageView pic;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        t1.findViewById(R.id.textView);
        t2.findViewById(R.id.textView4);
        t3.findViewById(R.id.textView5);
        b1.findViewById(R.id.button);
        pic.findViewById(R.id.imageView);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singOut();
            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personID = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            t1.setText("Name:"+personName);
            t2.setText("Email:"+personEmail);
            t3.setText("ID:"+personID);
            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);
        }
    }

    private void singOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Index.this, "SignOut", Toast.LENGTH_SHORT).show();
                        // ...
                        finish();
                    }
                });
    }
}