package hr.tvz.android.linxproject.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.linxproject.R
import hr.tvz.android.linxproject.adapter.ProductAdapter
import hr.tvz.android.linxproject.model.Product
import hr.tvz.android.linxproject.service.ServiceGenerator
import hr.tvz.android.linxproject.service.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CpuFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val productList = ArrayList<Product>()
    private val API_URL = "https://api.npoint.io/"
    private lateinit var models: MutableList<Product>

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_cpu, container, false)

        prepareItems(view)
        return view
    }

    private fun prepareItems(view: View) {
        val client: ServiceInterface = ServiceGenerator().createService(
            ServiceInterface::class.java,
            API_URL
        )

        val items: Call<MutableList<Product>> = client.fetchCpuModel()

        items.enqueue(object : Callback<MutableList<Product>> {
            override fun onResponse(
                call: Call<MutableList<Product>>,
                response: Response<MutableList<Product>>
            ) {
                if (response.isSuccessful) {
                    models = response.body()!!
                    for (model in models) {
                        productList.add(model)
                    }
                    val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
                    progressBar.visibility = View.GONE
                    recyclerView = view.findViewById(R.id.cpu_list_pane)

                    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
                    } else {
                        recyclerView.layoutManager = LinearLayoutManager(view.context)
                    }

                    recyclerView.adapter = ProductAdapter(productList) {}
                }
            }

            override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {
                Toast.makeText(this@CpuFragment.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}