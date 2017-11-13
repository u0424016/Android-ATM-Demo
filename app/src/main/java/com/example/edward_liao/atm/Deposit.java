package com.example.edward_liao.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Deposit extends AppCompatActivity {
    Button yes, cancel, clean;
    EditText money;

    int money_deposit;
    int enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        first();


    }


    //禁用系統返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    public void first() {
        money = (EditText) findViewById(R.id.editText_deposit);
        yes = (Button) findViewById(R.id.button_yes);
        cancel = (Button) findViewById(R.id.button_cancel);
        clean = (Button) findViewById(R.id.button_clean);

    }

    public void setYes(View view) {

        GlobalVariable gv = (GlobalVariable) getApplicationContext();
        money_deposit = gv.getMoney_total();
        enter = Integer.parseInt(money.getText().toString());
        money_deposit = money_deposit + enter;
        gv.setMoney_total(money_deposit);
        enter = 0;

        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);

    }

    public void setCancel(View view) {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);

    }

    public void setClean(View view) {
        money.setText("");

    }

}
