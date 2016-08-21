package com.example.sayla.coffeeorderapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_order;
    Button bt_increment;
    Button bt_decrement;
    TextView totalOrders;
    TextView  showAmmount;
    private String whichCbClicked;

    //CHECKBOXES
    CheckBox normalCoffee;
    CheckBox specialCoffee;

    private final int PRICE_FOR_SEPCIAL_ORDER=40;
    private final int PRICE_FOR_NORMAL_ORDER=20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_decrement = (Button)findViewById(R.id.bt_decrement);
        bt_increment = (Button)findViewById(R.id.bt_increment);
        bt_order = (Button)findViewById(R.id.bt_order);
        totalOrders = (TextView)findViewById(R.id.tv_total_orders);
        showAmmount = (TextView)findViewById(R.id.tv_show_ammount);
        normalCoffee = (CheckBox)findViewById(R.id.normal);
        specialCoffee = (CheckBox)findViewById(R.id.special);

        whichCbClicked = "no";

        normalCoffee.setOnClickListener(this);
        specialCoffee.setOnClickListener(this);
        bt_increment.setOnClickListener(this);
        bt_decrement.setOnClickListener(this);
        bt_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        int totalAmmount = 1;
        switch (id)
        {
            case R.id.bt_decrement:
                calculate("-");
                break;
            case R.id.bt_increment:
                calculate("+");
                break;
            case R.id.bt_order:
                totalAmmount = Integer.parseInt(totalOrders.getText().toString());
                if(whichCbClicked.equals("no"))
                {
                    showDialog("Please choose coffee orders");
                }
                else if(whichCbClicked.equals("special"))
                {
                    showAmmount.setText(totalAmmount*PRICE_FOR_SEPCIAL_ORDER+"");
                }
                else if(whichCbClicked.equals("normal"))
                {

                    showAmmount.setText(totalAmmount*PRICE_FOR_NORMAL_ORDER+"");
                }
                break;
            case R.id.special:
                specialCoffee.setChecked(true);
                normalCoffee.setChecked(false);
                whichCbClicked = "special";
                break;
            case R.id.normal:
                specialCoffee.setChecked(false);
                normalCoffee.setChecked(true);
                whichCbClicked = "normal";
                break;



        }
    }//end of onClick method..

    public void calculate(String flag)
    {
       String orderShow = totalOrders.getText().toString();
        int temp = 0;

        if(flag.equals("+"))
        {
            temp = Integer.parseInt(orderShow);
            temp = ++temp;
            totalOrders.setText(temp + "");
        }
        else
            if(flag.equals("-"))
            {
                if(!orderShow.equals("0")) {
                    temp = Integer.parseInt(orderShow);
                        if(temp>0){
                            --temp;
                        }
                        else
                        totalOrders.setText("0");

                    totalOrders.setText(temp + "");

                }
            }//end of else if
    }//end of calculate method..

    public void showDialog(String message)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
