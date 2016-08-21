package com.example.sayla.coffeeorderapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_order;
    Button bt_increment;
    Button bt_decrement;
    TextView totalOrders;
    TextView  showAmmount;
    private String whichCbClicked;
    private TextView name;
    private TextView quantity;
    private TextView order_type;
    private TextView date;
    private LinearLayout list;

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
        name = (TextView)findViewById(R.id.name);
        date = (TextView)findViewById(R.id.date);
        order_type = (TextView)findViewById(R.id.order_type);
        quantity = (TextView)findViewById(R.id.quantity);
        list = (LinearLayout)findViewById(R.id.list);



        whichCbClicked = "no";
        showInputDialog();
        normalCoffee.setOnClickListener(this);
        specialCoffee.setOnClickListener(this);
        bt_increment.setOnClickListener(this);
        bt_decrement.setOnClickListener(this);
        bt_order.setOnClickListener(this);


        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date currentDate = new Date();
        date.setText("Date: "+timeStampFormat.format(currentDate));
        list.setVisibility(View.GONE);
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

                showAmmount.setVisibility(View.GONE);
                if(whichCbClicked.equals("no"))
                {
                    showDialog("Please choose coffee orders");
                }
                else if(whichCbClicked.equals("special"))
                {
                    list.setVisibility(View.VISIBLE);
                    showAmmount.setVisibility(View.VISIBLE);
                    totalAmmount = Integer.parseInt(totalOrders.getText().toString());
                    showAmmount.setText(totalAmmount*PRICE_FOR_SEPCIAL_ORDER+"");
                    order_type.setText("Order Type: Special");
                    quantity.setText("Total Orders:"+totalOrders.getText().toString());
                }
                else if(whichCbClicked.equals("normal"))
                {
                    list.setVisibility(View.VISIBLE);
                    totalAmmount = Integer.parseInt(totalOrders.getText().toString());
                    showAmmount.setText("Total Ammount: "+totalAmmount*PRICE_FOR_NORMAL_ORDER+"");
                    quantity.setText("Total Orders:"+totalOrders.getText().toString());

                    order_type.setText("Order Type: Normal");

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


    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.text_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        name.setText("Name: " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
