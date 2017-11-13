package com.example.edward_liao.atm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class Pay extends AppCompatActivity {
    private Button pay, button_enter, clean, cancel;
    private EditText editText_pay;
    int money_pay, enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> BankList = ArrayAdapter.createFromResource
                (Pay.this, R.array.Payitem, android.R.layout.simple_spinner_dropdown_item);
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
        pay = (Button) findViewById(R.id.button_enter);
        clean = (Button) findViewById(R.id.button_clean);
        cancel = (Button) findViewById(R.id.button_cancel);
        button_enter = (Button) findViewById(R.id.button_enter);
        editText_pay = (EditText) findViewById(R.id.editText_pay);
    }

    public void setPay(View view) {


        if (enter == 0) {

        } else {


            new AlertDialog.Builder(Pay.this)
                    .setTitle("注意")
                    .setMessage("即將進行交易。確定付款?")
                    .setIcon(R.mipmap.erro)
                    .setPositiveButton("確定付款",
                            new DialogInterface.OnClickListener() {


                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    GlobalVariable gv = (GlobalVariable) getApplicationContext();
                                    money_pay = gv.getMoney_total();
                                    money_pay = money_pay - enter;
                                    gv.setMoney_total(money_pay);

                                    enter = 0;
                                    setBack_pay();
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


    public void setEnter_pay(View view) {

        final View item = LayoutInflater.from(Pay.this).inflate(R.layout.test, null);

        new AlertDialog.Builder(Pay.this)
                .setTitle("請輸入繳費金額")
                .setView(item)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GlobalVariable gv = (GlobalVariable) getApplicationContext();
                        money_pay = gv.getMoney_total();
                        EditText editText = item.findViewById(R.id.edit_text);
                        if (editText.getText().toString().equals("")) {


                            new AlertDialog.Builder(Pay.this)
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

                            if (enter > money_pay) {

                                new AlertDialog.Builder(Pay.this)
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
                                new AlertDialog.Builder(Pay.this)
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

                                editText_pay.setVisibility(View.VISIBLE);
                                editText_pay.setText(String.valueOf(enter));
                                Toast.makeText(getApplicationContext(), "輸入的金額是" + enter, Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                })
                .show();


    }

    public void setCancel_pay(View view) {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);
    }

    public void setClean_pay(View view) {
        editText_pay.setText("");

    }

    public void setBack_pay() {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);
    }
}

