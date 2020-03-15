package com.example.block_test;

import android.app.Activity;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class password extends baseActivity {
    EditText passText;
    Button manage_btn;

    @Override
    protected void init() {
        setContentView(R.layout.activity_password);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        passText = findViewById(R.id.passText);
        manage_btn = findViewById(R.id.manage_btn);

        passText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(passText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        manage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(passText.getText().toString()))
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                 else if (!firstPage.password.equals(passText.getText().toString()))
                    Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show();
                 else if (firstPage.password.equals(passText.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(),management.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
