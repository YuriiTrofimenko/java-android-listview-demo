package org.tyaa.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<User> users = new ArrayList<>();
        ListView userListView = findViewById(R.id.userListView);
        userListView.setAdapter(new ArrayAdapter<User>(MainActivity.this, android.R.layout.simple_list_item_1, users));
        for (Long i = 0L; i < 15; i++) {
            users.add(new User(i, "U" + i, "email" + i, new Date()));
        }
    }
}
