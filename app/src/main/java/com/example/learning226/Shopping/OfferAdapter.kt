package com.example.learning226.Shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class OfferAdapter(private val offerList: List<Offer>) :
    RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offerList[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int = offerList.size

    class OfferViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.offerImage)
        private val textView: TextView = view.findViewById(R.id.offerTitle)

        fun bind(offer: Offer) {
            imageView.setImageResource(offer.image)
            textView.text = offer.title
        }
    }
}
