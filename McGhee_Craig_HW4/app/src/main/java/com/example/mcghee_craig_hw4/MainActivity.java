package com.example.mcghee_craig_hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String name, email, pass;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor mEditor;
    private FrameLayout frame;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEdit = (EditText) findViewById(R.id.etName);
        final EditText emailEdit = (EditText) findViewById(R.id.etEmail);
        final EditText passwordEdit = (EditText) findViewById(R.id.etPassword);

        Button save = (Button)findViewById(R.id.save);
        Button ret = (Button)findViewById(R.id.retrieve);
        Button clear = (Button)findViewById(R.id.clear);

        frame = (FrameLayout)findViewById(R.id.frame);
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(activity);
                return false;
            }
        });

        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = sharedpreferences.edit();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEdit.getText().toString();
                email=emailEdit.getText().toString();
                pass=passwordEdit.getText().toString();

                mEditor.putString("name", name);
                mEditor.putString("email", email);
                mEditor.putString("pass", pass);
                mEditor.commit();
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setText(sharedpreferences.getString("name", ""));
                emailEdit.setText(sharedpreferences.getString("email", ""));
                passwordEdit.setText(sharedpreferences.getString("pass", ""));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setText("");
                emailEdit.setText("");
                passwordEdit.setText("");
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
