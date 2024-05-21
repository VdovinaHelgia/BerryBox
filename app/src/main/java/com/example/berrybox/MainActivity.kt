package com.example.berrybox

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.berrybox.databinding.ActivityMainBinding
import com.example.berrybox.db.Item
import com.example.berrybox.db.MainDb
import com.example.berrybox.frag.FavFragment
import com.example.berrybox.frag.InfoFragment
import com.example.berrybox.frag.MainFragment
import com.example.berrybox.frag.ShopFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mainbut.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, MainFragment.newInstance()).commit()
        }
        binding.shopbut.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, ShopFragment.newInstance()).commit()
        }
        binding.infobut.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, InfoFragment.newInstance()).commit()
        }
        binding.favbut.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, FavFragment.newInstance()).commit()
        }


        supportFragmentManager.beginTransaction().replace(R.id.place_holder, MainFragment.newInstance()).commit()

        val db = MainDb.getDb(this)
        db.getDao().getAllItems().asLiveData().observe(this) {


            if (it.isNullOrEmpty()) {

                val sharedPref = getSharedPreferences("myPrefs", MODE_PRIVATE)

                val imageResourceAnanas =
                    sharedPref.getString("image_resource_name", "ananas")
                val imageResourceIdAnanas =
                    getResources().getIdentifier(imageResourceAnanas, "drawable", packageName)

                val imageResourceArbuz =
                    sharedPref.getString("image_resource_name", "arbuz")
                val imageResourceIdArbuz =
                    getResources().getIdentifier(imageResourceArbuz, "drawable", packageName)

                val imageResourceBanana =
                    sharedPref.getString("image_resource_name", "banana")
                val imageResourceIdBanana =
                    getResources().getIdentifier(imageResourceBanana, "drawable", packageName)

                val imageResourceDina =
                    sharedPref.getString("image_resource_name", "dina")
                val imageResourceIdDina =
                    getResources().getIdentifier(imageResourceDina, "drawable", packageName)

                val imageResourceEshevika =
                    sharedPref.getString("image_resource_name", "eshevika")
                val imageResourceIdEshevika =
                    getResources().getIdentifier(imageResourceEshevika, "drawable", packageName)

                val imageResourceGranta =
                    sharedPref.getString("image_resource_name", "granta")
                val imageResourceIdGranta =
                    getResources().getIdentifier(imageResourceGranta, "drawable", packageName)

                val imageResourceKiwi =
                    sharedPref.getString("image_resource_name", "kiwi")
                val imageResourceIdKiwi =
                    getResources().getIdentifier(imageResourceKiwi, "drawable", packageName)

                val imageResourceKlubnika =
                    sharedPref.getString("image_resource_name", "klubnika")
                val imageResourceIdKlubnika =
                    getResources().getIdentifier(imageResourceKlubnika, "drawable", packageName)

                val imageResourceMalina =
                    sharedPref.getString("image_resource_name", "malina")
                val imageResourceIdMalina =
                    getResources().getIdentifier(imageResourceMalina, "drawable", packageName)

                val imageResourceMandarin =
                    sharedPref.getString("image_resource_name", "mandarin")
                val imageResourceIdMandarin =
                    getResources().getIdentifier(imageResourceMandarin, "drawable", packageName)

                val imageResourceMango =
                    sharedPref.getString("image_resource_name", "mango")
                val imageResourceIdMango =
                    getResources().getIdentifier(imageResourceMango, "drawable", packageName)

                val imageResourceOrange =
                    sharedPref.getString("image_resource_name", "orange")
                val imageResourceIdOrange =
                    getResources().getIdentifier(imageResourceOrange, "drawable", packageName)

                val orange = Item(
                    null,
                    "Апельсин","Мякоть — сочная, имеет сладко-кислый вкус с терпкими нотками.\n\nБразилия",
                    300,
                    imageResourceIdOrange,
                    0,0
                )
                val banana = Item(
                    null,
                    "Банан","Сладкие и ароматные бананы. Можно есть просто так, добавлять в смузи, каши, десерты и творог.\n\nЮго-Восточная Азия",
                    180,
                    imageResourceIdBanana,
                    0,0
                )
                val mandarin = Item(
                    null,
                    "Мандарины","Крупные мандарины. Тонкая кожура, поэтому их легко чистить. Мякоть сочная и сладкая, с небольшой кислинкой. У некоторых сортов могут быть косточки. \n\nМарокко\n" +
                            "Египет\n" +
                            "Турция\n" +
                            "Израиль",
                    240,
                    imageResourceIdMandarin,
                    0,0
                )
                val ananas = Item(
                    null,
                    "Ананас","Мякоть — отчетливо яркий и насыщенный вкус тропических фруктов, терпкий и сладкий, особенно в нижней части фрукта, где сахара больше.\n" +
                            "\n" +
                            "\n" +
                            "Южная Америка",
                    480,
                    imageResourceIdAnanas,
                    0,0
                )
                val mango = Item(
                    null,
                    "Манго","Фрукт сладкий, с легкой кислинкой, напоминает смесь персика с ананасом. Мякоть сочная, волокнистая, душистая.\n" +
                            "\n" +
                            "Индия\nФилиппины ",
                    330,
                    imageResourceIdMango,
                    0,0
                )
                val granta = Item(
                    null,
                    "Гранат","Зерна граната, благодаря сочной мякоти вокруг семян, имеют кисловатый вкус, похожий на клюквенный, но более сладкий. Они прекрасно сочетаются с салатами из листовых овощей или фруктов.\n" +
                            "\n" +
                            "Турция\nИран\nГрузия",
                    250,
                    imageResourceIdGranta,
                    0,0
                )
                val malina = Item(
                    null,
                    "Малина","Малина кисло-сладкая, сочная, с ярким ароматом. Косточки ее настолько малы, что практически не ощущаются на языке.\n\nРоссия",
                    900,
                    imageResourceIdMalina,
                    0,0
                )
                val klubnika = Item(
                    null,
                    "Клубника","Спелая, свежая клубника содержит во вкусе комбинацию фруктовых нот, карамели, пряностей и зелени. Некоторые сорта клубники имеют сильные вкусы ананаса.\n\nТурция\nБеларусь",
                    600,
                    imageResourceIdKlubnika,
                    0,0
                )
                val eshevika = Item(
                    null,
                    "Ежевика","Ежевика имеет сладковато-кислый вкус, и яркий лесной аромат. При разжевывании ягоды можно почувствовать мелкие косточки.\n\nРоссия",
                    630,
                    imageResourceIdEshevika,
                    0,0
                )
                val kiwi = Item(
                    null,
                    "Киви","Описывая вкус киви, говорят о сочетании тонов крыжовника, земляники, ананаса и яблока. Этот фрукт едят не только как десерт, но и подают к мясу, рыбе и сырам.\n\nКитай\nНовая Зеландия",
                    380,
                    imageResourceIdKiwi,
                    0,0
                )
                val arbuz = Item(
                    null,
                    "Арбуз","Сладкий, освежающий вкус, сочная мякоть.\n\nКазахстан\nУзбекистан\nТурция\nРоссия",
                    320,
                    imageResourceIdArbuz,
                    0,0
                )
                val dina = Item(
                    null,
                    "Дыня","Плоды с медовыми, ванильными, фруктовыми, земляничными оттенками вкуса.\n\nУзбекистан",
                    300,
                    imageResourceIdDina,
                    0,0
                )

                Thread {
                    db.getDao().insertItem(orange)
                    db.getDao().insertItem(banana)
                    db.getDao().insertItem(mandarin)
                    db.getDao().insertItem(ananas)
                    db.getDao().insertItem(mango)
                    db.getDao().insertItem(granta)
                    db.getDao().insertItem(malina)
                    db.getDao().insertItem(klubnika)
                    db.getDao().insertItem(eshevika)
                    db.getDao().insertItem(kiwi)
                    db.getDao().insertItem(arbuz)
                    db.getDao().insertItem(dina)
                }.start()


            }
        }
    }

}