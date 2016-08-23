package com.jiuzhang.guojing.awesometodo;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jiuzhang.guojing.awesometodo.models.Todo;

import java.util.List;

public class TodoListAdapter extends BaseAdapter {

    private Context context;
    private List<Todo> data;

    public TodoListAdapter(Context context, List<Todo> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);

            vh = new ViewHolder();
            vh.todoText = (TextView) convertView.findViewById(R.id.main_list_item_text);
            vh.doneCheckbox = (CheckBox) convertView.findViewById(R.id.main_list_item_check);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final Todo todo = (Todo) getItem(position);
        vh.todoText.setText(todo.text);

        if (todo.done) {
            vh.todoText.setPaintFlags(vh.todoText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            vh.todoText.setPaintFlags(vh.todoText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        vh.doneCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                todo.done = isChecked;
                notifyDataSetChanged();
            }
        });
        vh.doneCheckbox.setChecked(todo.done);

        return convertView;
    }

    private static class ViewHolder {
        TextView todoText;
        CheckBox doneCheckbox;
    }
}
