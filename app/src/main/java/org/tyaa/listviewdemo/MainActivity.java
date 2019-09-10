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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userNameEditText)
    EditText mUserNameEditText;
    @BindView(R.id.userEmailEditText)
    EditText mUserEmailEditText;
    @BindView(R.id.userListView)
    ListView mUserListView;

    List<User> users;
    ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        users = new ArrayList<>();

        adapter =
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

        mUserListView.setAdapter(adapter);
        mUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = users.get(position);
                mUserNameEditText.setText(user.name);
                mUserEmailEditText.setText(user.email);
                mUserListView.setTag(user.id);
            }
        });

        for (Long i = 0L; i < 15; i++) {
            users.add(new User(i, "U" + i, "email" + i, new Date()));
        }
    }

    @OnClick(R.id.userUpdateButton)
    public void onUserUpdateButtonClick(){
        User user =
                users.stream()
                        .filter((u) -> {
                            return u.id.equals((Long)(mUserListView.getTag()));
                        }).findFirst().get();
        user.name = mUserNameEditText.getText().toString();
        user.email = mUserEmailEditText.getText().toString();
        adapter.notifyDataSetChanged();
    }
}


