package com.bumil.babycryingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void pageChange(View view) {

        switch (view.getId()){

            case R.id.btn01:
                Intent intent = new Intent(MenuActivity.this, Info1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn02:
                intent = new Intent(MenuActivity.this, Info2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn03:
                intent = new Intent(MenuActivity.this, Info3Activity.class);
                startActivity(intent);
                break;
        }
    }
}
