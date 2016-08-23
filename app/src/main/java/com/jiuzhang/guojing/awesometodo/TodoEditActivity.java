package com.jiuzhang.guojing.awesometodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jiuzhang.guojing.awesometodo.models.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TodoEditActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    public static final String KEY_TODO = "todo";
    public static final String KEY_INDEX = "index";

    private TextView dateTv;
    private TextView timeTv;

    private Todo todo;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todo_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        todo = getIntent().getParcelableExtra(KEY_TODO);
        index = getIntent().getIntExtra(KEY_INDEX, 0);
        initUI();
    }

    private void initUI (){
        final EditText todoEdit = (EditText) findViewById(R.id.toto_detail_todo_edit);
        todoEdit.setText(todo.text);

//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("Yeah");

        dateTv = (TextView) findViewById(R.id.todo_detail_date);
        dateTv.setText(new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault()).format(todo.remindDate));
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new DatePickerDialog(
                        TodoEditActivity.this,
                        TodoEditActivity.this,
                        2010, 1, 1);
                dialog.show();
            }
        });

        timeTv = (TextView) findViewById(R.id.todo_detail_time);
        timeTv.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(todo.remindDate));
        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new TimePickerDialog(
                        TodoEditActivity.this,
                        TodoEditActivity.this,
                        0, 0, true);
                dialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.todo_detail_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.text = todoEdit.getText().toString();

                Intent result = new Intent();
                result.putExtra(KEY_TODO, todo);
                result.putExtra(KEY_INDEX, index);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });

        final CheckBox completeCb = (CheckBox) findViewById(R.id.todo_detail_complete);
        completeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    todoEdit.setPaintFlags(todoEdit.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    todoEdit.setTextColor(Color.GRAY);
                } else {
                    todoEdit.setPaintFlags(todoEdit.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    todoEdit.setTextColor(Color.WHITE);
                }
            }
        });

        View completeWrapper = findViewById(R.id.todo_detail_complete_wrapper);
        completeWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCb.setChecked(!completeCb.isChecked());
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(todo.remindDate);
        c.set(year, monthOfYear, dayOfMonth);
        todo.remindDate = c.getTime();
        dateTv.setText(new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault()).format(todo.remindDate));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(todo.remindDate);
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        todo.remindDate = c.getTime();
        timeTv.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(todo.remindDate));
    }
}
