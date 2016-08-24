package com.jiuzhang.guojing.awesometodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.jiuzhang.guojing.awesometodo.models.Todo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_TODO_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodoEditActivity.class);
                startActivityForResult(intent, REQ_CODE_TODO_EDIT);
            }
        });

        ListView listView = (ListView) findViewById(R.id.main_list_view);
        listView.setAdapter(new TodoListAdapter(this, mockData()));
    }

    @NonNull
    private List<Todo> mockData() {
        List<Todo> list = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm", Locale.getDefault());
        try {
            list.add(new Todo("lala", dateFormat.parse("2011 1 1 0:00")));
            list.add(new Todo("yiyi", dateFormat.parse("2013 3 8 0:00")));
            list.add(new Todo("shit", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 1", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 2", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 3", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 4", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 5", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 6", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 7", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 8", dateFormat.parse("2015 7 29 0:00")));
            list.add(new Todo("todo 9", dateFormat.parse("2015 7 29 0:00")));

            for (int i = 0; i < 1000; ++i) {
                list.add(new Todo("todo 9", dateFormat.parse("2015 7 29 0:00")));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }
}
