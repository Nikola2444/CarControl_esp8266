package com.example.carcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
public class Controller extends AppCompatActivity{
    String ip_toast = "entered ip is: ";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        Bundle userData = getIntent().getExtras();

        Toast.makeText(getApplicationContext(), ip_toast + userData.getString("ip"), Toast.LENGTH_LONG).show();
        final String entered_ip = userData.getString("ip");
        final int port = 301;
        Button F_button = (Button) findViewById(R.id.up_button);
        Button B_button = (Button) findViewById(R.id.down_button);
        Button L_button = (Button) findViewById(R.id.up_left_button);
        Button R_button = (Button) findViewById(R.id.up_rigth_button);
        Button down_rigth_button = (Button) findViewById(R.id.down_rigth_button);
        Button down_left_button = (Button) findViewById(R.id.down_left_button);
        Button end_button = (Button) findViewById(R.id.end_button);

        /*  FORWARD button detection*/
        F_button.setOnTouchListener(new View.OnTouchListener() {
            TCP_comm forward_tcp = new TCP_comm();
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    forward_tcp.TCP_send("F\0", entered_ip, port, event.getAction());
                return true;
            }
        });
        /* BACKWARD button detection*/
        B_button.setOnTouchListener(new View.OnTouchListener() {
            TCP_comm backward_tcp = new TCP_comm();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                backward_tcp.TCP_send("B\0", entered_ip, port, event.getAction());
                return true;
            }
        });
        /* Left button detection*/
        L_button.setOnTouchListener(new View.OnTouchListener() {
            TCP_comm left_tcp = new TCP_comm();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                left_tcp.TCP_send("L\0", entered_ip, port, event.getAction());
                return true;
            }
        });

        /* RIGHT button detection */
        R_button.setOnTouchListener(new View.OnTouchListener() {
            TCP_comm right_tcp = new TCP_comm();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                right_tcp.TCP_send("R\0", entered_ip, port, event.getAction());
                return true;
            }
        });
        /* down RIGHT button detection */
        down_rigth_button.setOnTouchListener(new View.OnTouchListener() {
            TCP_comm right_tcp = new TCP_comm();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                right_tcp.TCP_send("BR\0", entered_ip, port, event.getAction());
                return true;
            }
        });

        /* down RIGHT button detection */
        down_left_button.setOnTouchListener(new View.OnTouchListener() {
            TCP_comm right_tcp = new TCP_comm();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                right_tcp.TCP_send("BL\0", entered_ip, port, event.getAction());
                return true;
            }
        });
        /*Namesti instent da se vrati na prethodni ekran*/
        end_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
