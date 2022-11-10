package com.example.hotel;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button button;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView signUpTv;
    Animation animation;
    Animation animation1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_from_top);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        signUpTv=findViewById(R.id.signUpTv);
        button=findViewById(R.id.loginBtn);

        button.startAnimation(animation);
       // signUpTv.startAnimation(animation1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);

            }
        });

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);

            }
        });

    }
}