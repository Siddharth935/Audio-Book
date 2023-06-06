package com.royalfriends.bookies;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1000;
    private Button phone, google;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private AuthUI.IdpConfig phoneProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.btn_phone_sign_in);
        google = findViewById(R.id.btn_google_sign_in);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
//        locationEnabled();
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneAuthantication();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAuthantication();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = firebaseAuth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void emailAuthantication() {
        List<AuthUI.IdpConfig> emailProvider = Collections.singletonList(
                new AuthUI.IdpConfig.EmailBuilder().build());
        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(emailProvider)
                .build();
        uiLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> uiLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
    );

    private void phoneAuthantication() {
        phoneProvider = new AuthUI.IdpConfig.PhoneBuilder().build();
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(Collections.singletonList(phoneProvider)).build(), REQUEST_CODE);
    }
}