package dev.djakonystar.ruwlar

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Provider {

    private lateinit var ruwlarDatabase: RuwlarDatabase
    private lateinit var ruwlarDao: RuwlarDao

    private var mutableNewList: MutableLiveData<List<Ruw>> = MutableLiveData()
    val newList: LiveData<List<Ruw>> = mutableNewList

    fun getNewRuwlar(context: Context, id: Int) {
        if (!::ruwlarDatabase.isInitialized && !::ruwlarDao.isInitialized) {
            ruwlarDatabase = RuwlarDatabase.getInstance(context)
            ruwlarDao = ruwlarDatabase.ruwlarDao()
        }

        val newRuwlar = ruwlarDao.getRuwlarByParentId(id)
        mutableNewList.value = newRuwlar
    }
}