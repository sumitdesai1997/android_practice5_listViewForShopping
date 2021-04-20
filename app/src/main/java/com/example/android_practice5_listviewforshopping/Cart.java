package com.example.android_practice5_listviewforshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    ListView lvCart;
    TextView tvTotalAmount;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvCart = findViewById(R.id.lvCart);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnBack = findViewById(R.id.btnBack);

        tvTotalAmount.setText(String.valueOf(MainActivity.finalPrice));

        // FOR LIST VIEW
        ItemAdapter itemAdapter = new ItemAdapter(this,MainActivity.cartList);
        lvCart.setAdapter(itemAdapter);
        lvCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.finalPrice -= MainActivity.cartList.get(position).getPrice();
                tvTotalAmount.setText(String.valueOf(MainActivity.finalPrice));
                MainActivity.cartList.remove(position);
                lvCart.setAdapter(itemAdapter);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}