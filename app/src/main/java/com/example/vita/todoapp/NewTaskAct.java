package com.example.vita.todoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewTaskAct extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static Map<String, String> daydata = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("タスクの追加");
        setSupportActionBar(toolbar);

        //ぜったい
        final CheckBox absolute_checkbox = (CheckBox)findViewById(R.id.absolutetask);
        //やる
        final CheckBox normal_checkbox = (CheckBox)findViewById(R.id.normaltask);
        //やりたくない
        final CheckBox donot_checkbox = (CheckBox)findViewById(R.id.donottask);
        //時刻を設定する
        final CheckBox setdate_checkbox = (CheckBox)findViewById(R.id.setdate);

        //ぜったいにチェックがついた時
        absolute_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal_checkbox.setChecked(false);
                donot_checkbox.setChecked(false);
                if (absolute_checkbox.isChecked()==true){
                    Toast toast = Toast.makeText(NewTaskAct.this,"ぜったいにチェックをつけました",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //やるにチェックがついた時
        normal_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                absolute_checkbox.setChecked(false);
                donot_checkbox.setChecked(false);
                if(normal_checkbox.isChecked()==true){
                    Toast toast = Toast.makeText(NewTaskAct.this,"まあやるにチェックをつけました",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //やりたくないにチェックがついた時
        donot_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                absolute_checkbox.setChecked(false);
                normal_checkbox.setChecked(false);
                if(donot_checkbox.isChecked()==true){
                    Toast toast = Toast.makeText(NewTaskAct.this,"やりたくないにチェックをつけました",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //設定するにチェックがついた時
        setdate_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setdate_checkbox.isChecked()==true) {
                    DialogFragment newFragment = new DatePick();
                    newFragment.show(getSupportFragmentManager(), "datePicker");

                    //キャンセルが押された時用にいつでもいいに一度変えておく
                    setdate_checkbox.setText("設定する");
                    setdate_checkbox.setChecked(false);
                }else{
                    setdate_checkbox.setText("設定する");
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_new_task_act,menu);
        return true;
    }

    //toolbarの動作
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.task_add:
                //SharedPreferences newtask_data = getPreferences(MODE_PRIVATE);
                //SharedPreferences.Editor task_save = newtask_data.edit();
                System.out.println(daydata.get("year"));
                daydata.clear();
                Intent intent = new Intent(NewTaskAct.this,ToDoMainAct.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        return true;
    }

    //時間を設定する
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final CheckBox setdate_checkbox = (CheckBox)findViewById(R.id.setdate);
        setdate_checkbox.setText(String.valueOf(year) + "/ " + String.valueOf(month+1) + "/ " + String.valueOf(dayOfMonth));
        daydata.put("year",String.valueOf(year));
        daydata.put("month",String.valueOf(month));
        daydata.put("dayOfMonth",String.valueOf(dayOfMonth));
        setdate_checkbox.setChecked(true);
    }

}
