package hr.tvz.android.linxproject.activity

import android.content.ContentValues
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import hr.tvz.android.linxproject.R
import hr.tvz.android.linxproject.databinding.ActivityMainBinding
import hr.tvz.android.linxproject.receiver.WifiStateReceiver

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var wifiState: WifiStateReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(ContentValues.TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.getResult().toString()
                Log.d(ContentValues.TAG, token)
            })

        binding.appBarMain.fab.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)

            builder.setMessage("Subscribe to our newsletter?")
                .setPositiveButton("Yes") { dialog, which ->
                    Intent().also { intent ->
                        intent.setAction("com.example.broadcast.MY_NOTIFICATION")
                        intent.putExtra("data", "Intent message")
                        sendBroadcast(intent)
                    }

                    Toast.makeText(
                        applicationContext,
                        "Subscribed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("No") { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        "Your loss.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .create()
                .show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_cpu, R.id.nav_gpu
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        wifiState = WifiStateReceiver()
        Firebase.messaging.isAutoInitEnabled = true

        IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION).also {
            registerReceiver(wifiState, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}