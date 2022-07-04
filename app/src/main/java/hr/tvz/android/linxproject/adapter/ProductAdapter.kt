package hr.tvz.android.linxproject.adapter

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import hr.tvz.android.linxproject.R
import hr.tvz.android.linxproject.activity.ProductDetailActivity
import hr.tvz.android.linxproject.model.Product


internal class ProductAdapter(private var productList: List<Product>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productNameView: TextView = view.findViewById(R.id.productNameView)
        var productPriceView: TextView = view.findViewById(R.id.productPriceView)
        val productImageView: SimpleDraweeView = view.findViewById(R.id.productImageView)
        var listItem: LinearLayout = view.findViewById(R.id.linearLayout)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val productView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item, parent, false)
        return MyViewHolder(productView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]
        holder.productImageView.setImageURI(Uri.parse(product.photo?.filename))
        holder.productNameView.text = product.name
        holder.productPriceView.text = String.format("%.2f", product.price) + " $"
        holder.listItem.setOnClickListener {
            holder.productNameView.animate().setDuration(100).scaleX(1.1f).start()
            Handler().postDelayed({
                clickListener(productList[position].toString())
            }, 100)

            val intent = Intent(holder.listItem.context, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT", productList[position])
            holder.listItem.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}