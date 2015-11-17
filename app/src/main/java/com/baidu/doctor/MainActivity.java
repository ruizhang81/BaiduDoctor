package com.baidu.doctor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.doctor.view.BodyView;
import com.baidu.doctor.view.BodyViewMan;
import com.baidu.doctor.view.BodyViewManBack;
import com.baidu.doctor.view.BodyViewManHead;
import com.baidu.doctor.view.BodyViewWoMan;
import com.baidu.doctor.view.BodyViewWoManBack;
import com.baidu.doctor.view.BodyViewWoManHead;

public class MainActivity extends AppCompatActivity {

    private BodyView bodyMan, bodyWoMan, bodyManBack, bodyWoManBack, bodyManHead, bodyWoManHead;
    private TextView switchBtn;
    private Button man;
    private Button woman;
    private ImageView front_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initBody();

    }

    private void initBody() {
        bodyMan = (BodyViewMan) findViewById(R.id.body_man);
        bodyWoMan = (BodyViewWoMan) findViewById(R.id.body_woman);
        bodyManBack = (BodyViewManBack) findViewById(R.id.body_man_back);
        bodyWoManBack = (BodyViewWoManBack) findViewById(R.id.body_woman_back);
        bodyManHead = (BodyViewManHead) findViewById(R.id.body_man_head);
        bodyWoManHead = (BodyViewWoManHead) findViewById(R.id.body_woman_head);
        switchBtn = (TextView) findViewById(R.id.switch_btn);
        man = (Button) findViewById(R.id.man);
        woman = (Button) findViewById(R.id.woman);
        front_back = (ImageView) findViewById(R.id.front_back);
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bodyManHead.getVisibility() == View.VISIBLE) {
                    display(bodyMan);
                } else if (bodyWoManHead.getVisibility() == View.VISIBLE) {
                    display(bodyWoMan);
                }
                man.setVisibility(View.VISIBLE);
                woman.setVisibility(View.VISIBLE);
                front_back.setVisibility(View.VISIBLE);
                switchBtn.setVisibility(View.GONE);
            }
        });
        BodyView.OnClickBodyPartListener listener = new BodyView.OnClickBodyPartListener() {
            @Override
            public void onClickBodyPart(String bodyPartId) {
                if (bodyPartId != null) {
                    if ("-1".equals(bodyPartId) || "-2".equals(bodyPartId)) {
                        display("-1".equals(bodyPartId) ? bodyManHead : bodyWoManHead);
                        man.setVisibility(View.GONE);
                        woman.setVisibility(View.GONE);
                        front_back.setVisibility(View.GONE);
                        switchBtn.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(MainActivity.this, "点击 " + bodyPartId, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        bodyMan.setOnClickBodyPartListener(listener);
        bodyManBack.setOnClickBodyPartListener(listener);
        bodyManHead.setOnClickBodyPartListener(listener);
        bodyWoMan.setOnClickBodyPartListener(listener);
        bodyWoManBack.setOnClickBodyPartListener(listener);
        bodyWoManHead.setOnClickBodyPartListener(listener);
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                man.setBackgroundResource(R.drawable.round_button_press);
                woman.setBackgroundResource(R.drawable.round_button);
                man.setTextColor(getResources().getColor(R.color.white));
                woman.setTextColor(getResources().getColor(R.color.blue));
                if (bodyWoMan.getVisibility() == View.VISIBLE) {
                    display(bodyMan);
                } else if (bodyWoManBack.getVisibility() == View.VISIBLE) {
                    display(bodyManBack);
                } else {
                }
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                man.setBackgroundResource(R.drawable.round_button);
                woman.setBackgroundResource(R.drawable.round_button_press);
                man.setTextColor(getResources().getColor(R.color.blue));
                woman.setTextColor(getResources().getColor(R.color.white));
                if (bodyMan.getVisibility() == View.VISIBLE) {
                    display(bodyWoMan);
                } else if (bodyManBack.getVisibility() == View.VISIBLE) {
                    display(bodyWoManBack);
                } else {
                }
            }
        });
        front_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bodyMan.getVisibility() == View.VISIBLE) {
                    display(bodyManBack);
                    front_back.setImageResource(R.drawable.intelligent_diagnosis_btn_back);
                } else if (bodyManBack.getVisibility() == View.VISIBLE) {
                    display(bodyMan);
                    front_back.setImageResource(R.drawable.intelligent_diagnosis_btn_front);
                } else if (bodyWoMan.getVisibility() == View.VISIBLE) {
                    display(bodyWoManBack);
                    front_back.setImageResource(R.drawable.intelligent_diagnosis_btn_back);
                } else if (bodyWoManBack.getVisibility() == View.VISIBLE) {
                    display(bodyWoMan);
                    front_back.setImageResource(R.drawable.intelligent_diagnosis_btn_front);
                } else {
                }
            }
        });
    }

    private void display(View view) {
        bodyMan.setVisibility(View.GONE);
        bodyManBack.setVisibility(View.GONE);
        bodyManHead.setVisibility(View.GONE);
        bodyWoMan.setVisibility(View.GONE);
        bodyWoManBack.setVisibility(View.GONE);
        bodyWoManHead.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
