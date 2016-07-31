package com.example.sayla.coffeeorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_order;
    Button bt_increment;
    Button bt_decrement;
    TextView totalOrders;
    TextView  showAmmount;
    private int total = 0;
    private final int PRICE_PER_ORDER=15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_decrement = (Button)findViewById(R.id.bt_decrement);
        bt_increment = (Button)findViewById(R.id.bt_increment);
        bt_order = (Button)findViewById(R.id.bt_order);
        totalOrders = (TextView)findViewById(R.id.tv_total_orders);
        showAmmount = (TextView)findViewById(R.id.tv_show_ammount);


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
                showAmmount.setText(totalAmmount*PRICE_PER_ORDER+"");
                break;
        }
    }//end of onClick method..

    public void calculate(String flag)
    {
       String orderShow = totalOrders.getText().toString();
        int temp = 0;

        if(flag.equals("+"))
        {
           // if(!orderShow.equals("0")) {
               // ++total;
                temp = Integer.parseInt(orderShow);

            Toast.makeText(getApplicationContext(), "total="+total, Toast.LENGTH_SHORT).show();
                temp = ++temp;
                totalOrders.setText(temp + "");
                Toast.makeText(getApplicationContext(), "Incremented", Toast.LENGTH_SHORT).show();
//            }else{
//                totalOrders.setText(++total);
//            }
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
                Toast.makeText(getApplicationContext(), "Decrement", Toast.LENGTH_SHORT).show();
            }//end of else if
    }//end of calculate method..

}
