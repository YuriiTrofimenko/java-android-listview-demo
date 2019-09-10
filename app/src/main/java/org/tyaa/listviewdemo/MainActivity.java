package org.tyaa.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mUserNameEditText;
    private EditText mUserEmailEditText;
    private Button mUserUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserNameEditText =
                MainActivity.this.findViewById(R.id.userNameEditText);
        mUserEmailEditText =
                MainActivity.this.findViewById(R.id.userEmailEditText);
        mUserUpdateButton =
                MainActivity.this.findViewById(R.id.userUpdateButton);

        final List<User> users = new ArrayList<>();
        ListView userListView = findViewById(R.id.userListView);

        final ArrayAdapter<User> adapter =
            new ArrayAdapter<User>(MainActivity.this, R.layout.user_list_item, users){
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
            };

        userListView.setAdapter(adapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = users.get(position);
                mUserNameEditText.setText(user.name);
                mUserEmailEditText.setText(user.email);
                mUserUpdateButton.setTag(user.id);
            }
        });

        mUserUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user =
                        users.stream()
                                .filter((u) -> {
                                    return u.id.equals((Long)(mUserUpdateButton.getTag()));
                                }).findFirst().get();
                user.name = mUserNameEditText.getText().toString();
                user.email = mUserEmailEditText.getText().toString();
                adapter.notifyDataSetChanged();
            }
        });

        for (Long i = 0L; i < 15; i++) {
            users.add(new User(i, "U" + i, "email" + i, new Date()));
        }
    }
}
