package hr.tvz.android.linxproject.activity

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hr.tvz.android.linxproject.R
import hr.tvz.android.linxproject.adapter.ReviewAdapter
import hr.tvz.android.linxproject.databinding.ActivityProductDetailBinding
import hr.tvz.android.linxproject.model.Product

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.chime_sound)
        mediaPlayer.isLooping = false
        mediaPlayer.start()

        val product: Product = intent.extras?.getParcelable("PRODUCT")!!

        binding.productDetailName.text = product.name
        binding.productDetailImage.setImageURI(Uri.parse(product.photo?.filename))
        binding.productDetailPrice.text = "Price: " + String.format("%.2f", product.price) + " $"
        binding.productDetailDescription.text = product.description
        binding.productDetailReviews.layoutManager = LinearLayoutManager(this)
        binding.productDetailReviews.adapter = ReviewAdapter(product.reviews) {}
    }

    override fun onBackPressed() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        super.onBackPressed()
    }
}