package com.example.android_practice5_listviewforshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvItem;
    SeekBar sbQty;
    TextView tvQty, tvTotAmount, tvItemAmount;
    Button btnAddCart, btnShowCart;

    ArrayList<Item> itemList = new ArrayList<>();
    double itemAmount = 0;
    public static double finalPrice = 0;
    public static ArrayList<Item> cartList = new ArrayList<>();
    public static Item cureentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItem = findViewById(R.id.lvItem);
        sbQty = findViewById(R.id.sbQty);
        tvQty = findViewById(R.id.tvQty);
        tvTotAmount = findViewById(R.id.tvTotAmount);
        tvItemAmount = findViewById(R.id.tvItemAmount);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnShowCart = findViewById(R.id.btnShowCart);

        tvQty.setText("1");
        tvItemAmount.setText("Item not selected");
        tvTotAmount.setText(String.valueOf(finalPrice));

        fillData();

        // FOR LIST VIEW
        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        lvItem.setAdapter(itemAdapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sbQty.setProgress(1);
                tvQty.setText("1");
                tvItemAmount.setText(String.valueOf(itemList.get(position).getPrice()));
                itemAmount = itemList.get(position).getPrice();

                cureentItem = new Item(itemList.get(position).getName(),itemList.get(position).getImg(), itemList.get(position).getPrice());
            }
        });

        sbQty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvQty.setText(String.valueOf(progress));
                tvItemAmount.setText(String.valueOf(itemAmount*progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnAddCart.setOnClickListener(new ButtonEvents());
        btnShowCart.setOnClickListener(new ButtonEvents());

    }

    public void fillData(){
        itemList.add(new Item("Mask","mask",1.5));
        itemList.add(new Item("Face shield","faceshield",3.0));
        itemList.add(new Item("Sanitizer","sanitizer",5.0));
        itemList.add(new Item("Gloves","gloves",2.5));
        itemList.add(new Item("PPE Kit","ppe",5.0));
    }

    private class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnAddCart){
                finalPrice += Double.parseDouble(tvItemAmount.getText().toString());
                tvTotAmount.setText(String.valueOf(finalPrice));
                boolean exist = false;

                for(Item cart: cartList){
                    if(cart.getName().equalsIgnoreCase(cureentItem.getName())){
                        exist = true;
                        cart.setPrice(cart.getPrice() + Double.parseDouble(tvItemAmount.getText().toString()));
                    }
                }

                if(!exist){
                    cureentItem.setPrice(Double.parseDouble(tvItemAmount.getText().toString()));
                    cartList.add(cureentItem);
                }

            } else {
                Intent intent = new Intent(getBaseContext(), Cart.class);
                startActivity(intent);
            }
        }
    }
}