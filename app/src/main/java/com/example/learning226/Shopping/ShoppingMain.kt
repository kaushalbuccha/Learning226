package com.example.learning226.Shopping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learning226.R
import androidx.recyclerview.widget.RecyclerView

class ShoppingMain : AppCompatActivity() {
    private lateinit var recyclerViewOffers: RecyclerView
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var offerAdapter: OfferAdapter
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_main)

        recyclerViewOffers = findViewById(R.id.recyclerViewOffers)
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)

        val offerList = listOf(
            Offer("50% off", R.drawable.pizza_card),
            Offer("Buy 1 Get 1", R.drawable.pizza_card),
            Offer("20% Cashback", R.drawable.pizza_card)
        )


        val productList = listOf(
            Product("Product 1", R.drawable.pizza, "₹200"),
            Product("Product 2", R.drawable.pizza, "₹300"),
            Product("Product 3", R.drawable.pizza, "₹400"),
            Product("Product 4", R.drawable.pizza, "₹500"),
            Product("Product 5", R.drawable.pizza, "₹300"),
            Product("Product 6", R.drawable.pizza, "₹300"),
            Product("Product 7", R.drawable.pizza, "₹300"),
            Product("Product 8", R.drawable.pizza, "₹300"),
            Product("Product 9", R.drawable.pizza, "₹300")
        )


        offerAdapter = OfferAdapter(offerList)
        recyclerViewOffers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewOffers.adapter = offerAdapter


        productAdapter = ProductAdapter(productList)
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProducts.adapter = productAdapter
    }


}