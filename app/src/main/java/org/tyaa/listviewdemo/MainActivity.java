package org.tyaa.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<User> users = new ArrayList<>();
        ListView userListView = findViewById(R.id.userListView);
        userListView.setAdapter(new ArrayAdapter<User>(MainActivity.this, R.layout.user_list_item, users){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.user_list_item, parent, false);
                }
                TextView nameTextView = convertView.findViewById(R.id.nameTextView);
                nameTextView.setText(users.get(position).name);
                TextView emailTextView = convertView.findViewById(R.id.emailTextView);
                emailTextView.setText(users.get(position).email);
                TextView birthdayTextView = convertView.findViewById(R.id.birthdayTextView);
                birthdayTextView.setText(users.get(position).birthday.toString());
                return convertView;
            }
        });
        for (Long i = 0L; i < 15; i++) {
            users.add(new User(i, "U" + i, "email" + i, new Date()));
        }
    }
}
