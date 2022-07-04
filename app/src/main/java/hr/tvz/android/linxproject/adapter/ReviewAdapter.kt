package hr.tvz.android.linxproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.linxproject.R
import hr.tvz.android.linxproject.model.Review

internal class ReviewAdapter(private var reviewList: ArrayList<Review>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var reviewText: TextView = view.findViewById(R.id.reviewTitleView)
        var reviewRating: RatingBar = view.findViewById(R.id.reviewRatingBar)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val reviewView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_review, parent, false)
        return MyViewHolder(reviewView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = reviewList[position]
        holder.reviewText.text = review.title
        holder.reviewRating.rating = review.rating!!
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}