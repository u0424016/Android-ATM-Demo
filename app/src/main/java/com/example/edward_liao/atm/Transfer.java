package com.example.edward_liao.atm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Transfer extends AppCompatActivity {
    private Button transfer, button_enter, clean, cancel;
    private EditText editText_transfer;
    int money_transfer, enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> BankList = ArrayAdapter.createFromResource(Transfer.this, R.array.Bank, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(BankList);


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
        transfer = (Button) findViewById(R.id.button_enter);
        clean = (Button) findViewById(R.id.button_clean);
        cancel = (Button) findViewById(R.id.button_cancel);
        button_enter = (Button) findViewById(R.id.button_enter);
        editText_transfer = (EditText) findViewById(R.id.editText_transfer);
    }

    public void setTransfer(View view) {


        if (enter == 0) {

        } else {


            new AlertDialog.Builder(Transfer.this)
                    .setTitle("注意")
                    .setMessage("您的錢即將轉出。確定轉出?")
                    .setIcon(R.mipmap.erro)
                    .setPositiveButton("確定轉出",
                            new DialogInterface.OnClickListener() {


                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    GlobalVariable gv = (GlobalVariable) getApplicationContext();
                                    money_transfer = gv.getMoney_total();
                                    money_transfer = money_transfer - enter;
                                    gv.setMoney_total(money_transfer);
                                    enter = 0;
                                    setBack();
                                    // TODO Auto-generated method stub
                                }
                            })

                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();


        }

    }

    public void setEnter(View view) {

        final View item = LayoutInflater.from(Transfer.this).inflate(R.layout.test, null);


        new AlertDialog.Builder(Transfer.this)
                .setTitle("請輸入欲轉帳金額")
                .setView(item)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GlobalVariable gv = (GlobalVariable) getApplicationContext();
                        money_transfer = gv.getMoney_total();
                        EditText editText = item.findViewById(R.id.edit_text);
                        if (editText.getText().toString().equals("")) {


                            new AlertDialog.Builder(Transfer.this)
                                    .setTitle("錯誤")
                                    .setMessage("輸入金額不能為空。請重新輸入")
                                    .setIcon(R.mipmap.erro)
                                    .setNegativeButton("確定",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    // TODO Auto-generated method stub
                                                }
                                            }).show();

                        } else {
                            enter = Integer.parseInt(editText.getText().toString());

                            if (enter > money_transfer) {

                                new AlertDialog.Builder(Transfer.this)
                                        .setTitle("錯誤")
                                        .setMessage("餘額不足。請重新輸入")
                                        .setIcon(R.mipmap.erro)
                                        .setNegativeButton("確定",
                                                new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which) {
                                                        // TODO Auto-generated method stub
                                                    }
                                                }).show();

                            } else if (enter == 0) {
                                new AlertDialog.Builder(Transfer.this)
                                        .setTitle("錯誤")
                                        .setMessage("輸入金額不能為0。請重新輸入")
                                        .setIcon(R.mipmap.erro)
                                        .setNegativeButton("確定",
                                                new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which) {
                                                        // TODO Auto-generated method stub
                                                    }
                                                }).show();

                            } else {

                                editText_transfer.setVisibility(View.VISIBLE);
                                editText_transfer.setText(String.valueOf(enter));
                                Toast.makeText(getApplicationContext(), "輸入的金額是" + enter, Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                })
                .show();


    }

    public void setCancel(View view) {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);
    }

    public void setClean(View view) {
        editText_transfer.setText("");

    }

    public void setBack() {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);
    }
}
