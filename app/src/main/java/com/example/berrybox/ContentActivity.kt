package com.example.berrybox

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.berrybox.adapter.ItemFruit
import com.example.berrybox.databinding.ActivityContentBinding
import com.example.berrybox.db.Item
import com.example.berrybox.db.MainDb

class ContentActivity : AppCompatActivity() {

    lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = MainDb.getDb(this)
        val item = intent.getSerializableExtra("item") as ItemFruit
        var isfav = item.favorite
        var isshop = item.shop
        var isDataHandledParts = true
        binding.apply {
            imageView7.setImageResource(item.avatarUrl)
            textView12.text = item.nameN
            textView13.text = "1кг - " + item.cost.toString() + " ₽"
            textView16.text = item.description
            textView16.setMovementMethod(ScrollingMovementMethod())
            if (isfav == 0) {
                imageView8.setImageResource(R.drawable.favorite__1_)
            }
            else{
                imageView8.setImageResource(R.drawable.favorite__3_)
            }
            if (isshop == 0){
                textView15.setText("Добавить в корзину")

            }
            else{

                textView15.setText("В корзине ")
            }

            imageView8.setOnClickListener{
                val itemp = Item(item.id, item.nameN,item.description,item.cost, item.avatarUrl, 1, item.shop)
                val itemm = Item(item.id, item.nameN,item.description,item.cost, item.avatarUrl, 0, item.shop)
                isDataHandledParts = true
                if (isfav == 0){
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                        if(isDataHandledParts){
                            Thread{
                                db.getDao().insertItem(itemp)
                            }.start()
                            imageView8.setImageResource(R.drawable.favorite__3_)
                            isfav = 1
                        }
                        isDataHandledParts = false
                    }
                }
                else{
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                        if(isDataHandledParts) {
                            Thread {
                                db.getDao().insertItem(itemm)
                            }.start()
                            imageView8.setImageResource(R.drawable.favorite__1_)
                            isfav = 0
                        }
                        isDataHandledParts = false
                    }
                }
            }

            shopbuttoncont.setOnClickListener{
                val itemcp = Item(item.id, item.nameN,item.description,item.cost, item.avatarUrl, item.favorite, 1)
                val itemcm = Item(item.id, item.nameN,item.description,item.cost, item.avatarUrl, item.favorite, 0)
                isDataHandledParts = true
                if (isshop == 0){
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                        if(isDataHandledParts) {
                            Thread{
                                db.getDao().insertItem(itemcp)
                            }.start()
                            textView15.setText("В корзине")
                            isshop = 1
                        }
                        isDataHandledParts = false
                    }
                }
                else {
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity) {
                        if(isDataHandledParts) {
                            Thread {
                                db.getDao().insertItem(itemcm)
                            }.start()
                            textView15.setText("Добавить в корзину")
                            isshop = 0
                        }
                        isDataHandledParts = false
                    }
                }
            }
            imageView13.setOnClickListener {
                finish()
            }
        }
    }
}