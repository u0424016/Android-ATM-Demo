package com.example.edward_liao.atm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Withdraw extends AppCompatActivity {
    Button yes, cancel, clean;
    EditText money;

    int money_withdraw;
    int enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

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
        money = (EditText) findViewById(R.id.editText_withdraw);
        yes = (Button) findViewById(R.id.button_yes);
        cancel = (Button) findViewById(R.id.button_cancel);
        clean = (Button) findViewById(R.id.button_clean);

    }

    public void setYes(View view) {

        GlobalVariable gv = (GlobalVariable) getApplicationContext();
        money_withdraw = gv.getMoney_total();
        enter = Integer.parseInt(money.getText().toString());

        if (enter > money_withdraw) {
            new AlertDialog.Builder(Withdraw.this)
                    .setTitle("錯誤")
                    .setMessage("餘額不足。請重新輸入")
                    .setIcon(R.mipmap.erro)
                    .setNegativeButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    money.setText("");
                                    // TODO Auto-generated method stub
                                }
                            }).show();

        } else {

            money_withdraw = money_withdraw - enter;
            gv.setMoney_total(money_withdraw);
            enter = 0;

            finish();
            Intent back = new Intent(this, FunctionActivity.class);
            startActivity(back);

        }


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