package dev.djakonystar.ruwlar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.djakonystar.ruwlar.Keys.PARENT_ID
import dev.djakonystar.ruwlar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var ruwlarDatabase: RuwlarDatabase
    private lateinit var ruwlarDao: RuwlarDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Ruwlar", Context.MODE_PRIVATE)
        ruwlarDatabase = RuwlarDatabase.getInstance(this)
        ruwlarDao = ruwlarDatabase.ruwlarDao()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()
    }

    override fun onBackPressed() {
        val parentId = sharedPreferences.getInt(PARENT_ID, -1)

        if (parentId == 0) {
            finish()
        } else {
            val grandParentId = ruwlarDao.getParentId(parentId)
            Provider.getNewRuwlar(this, grandParentId)
        }
    }
}
