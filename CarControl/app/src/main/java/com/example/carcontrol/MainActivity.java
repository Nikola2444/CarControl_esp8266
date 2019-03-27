package com.example.carcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    protected Button IpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void ClickMe(View view){
        Intent i = new Intent(this, Controller.class); // this here sets to which activity to switch
        final EditText ip_input = (EditText) findViewById(R.id.editText);
        String ip = ip_input.getText().toString();
        i.putExtra("ip", ip);
        startActivity(i);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
