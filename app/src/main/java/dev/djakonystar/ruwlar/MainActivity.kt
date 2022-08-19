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
    private val adapter by lazy { RuwlarAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Ruwlar", Context.MODE_PRIVATE)
        ruwlarDatabase = RuwlarDatabase.getInstance(this)
        ruwlarDao = ruwlarDatabase.ruwlarDao()

        binding.recyclerView.adapter = adapter

        getNewRuwlar(0)

        adapter.setOnItemClickListener {
            getNewRuwlar(it.id)
        }
    }

    override fun onBackPressed() {
        val parentId = sharedPreferences.getInt(PARENT_ID, -1)

        if (parentId == 0) {
            finish()
        } else {
            val grandParentId = ruwlarDao.getParentId(parentId)
            getNewRuwlar(grandParentId)
        }
    }

    fun getNewRuwlar(id: Int) {
        val newRuwlar = ruwlarDao.getRuwlarByParentId(id)
        adapter.models = newRuwlar

        newRuwlar.firstOrNull()?.let {
            sharedPreferences.edit().putInt(PARENT_ID, it.parentId).apply()
        }
    }
}
