package kr.ac.kumoh.s20180489.w120101recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    private val _list = MutableLiveData<ArrayList<String>>()
    val list: LiveData<ArrayList<String>>
        get() = _list

    init {
        _list.value = ArrayList<String>()
    }

    fun add(song: String) {
        _list.value?.add(song)
    }

/*    fun getSong(i: Int) = _list.value?.get(i)
    fun getSize() = _list.value?.size*/
}