package uz.muhammadziyo.homeworkd311

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.muhammadziyo.homeworkd311.adapter.RvAdapter
import uz.muhammadziyo.homeworkd311.adapter.SetRv
import uz.muhammadziyo.homeworkd311.databinding.ActivityMainBinding
import uz.muhammadziyo.homeworkd311.models.User


class MainActivity : AppCompatActivity() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: RvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        loadList()
    }

    private fun loadList() {
        VolleyLog.DEBUG = true
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://api.github.com/users",
            null,
            {
                val type = object : TypeToken<ArrayList<User>>() {}.type
                val list = Gson().fromJson<ArrayList<User>>(it.toString(),type)
                userAdapter = RvAdapter(list, object : SetRv{


                    override fun imageJoyla(user: User, imageView: ImageView) {

                        loadImage(imageView,user.avatar_url)
                    }
                })
                binding.rv.adapter = userAdapter
            },
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }


        )
        requestQueue.add(jsonArrayRequest)

    }
    fun loadImage(imageView: ImageView, url:String){
        val imageRequest = ImageRequest (url,
            {
                imageView.setImageBitmap(it)
            },
            0,0,ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888
        ) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
        requestQueue.add(imageRequest)

    }

}