package kr.ac.kumoh.s20180489.w120101recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20180489.w120101recyclerview.databinding.ActivityMainBinding
import kr.ac.kumoh.s20180489.w120101recyclerview.databinding.SongListDataBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model: ListViewModel by viewModels()
    private val songAdapter by lazy { model.list.value?.let { SongAdapter(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.add("소주한잔")
        model.add("소주한잔")
        model.add("소주한잔")
        model.add("태스형")

        model.list.observe(this, Observer<ArrayList<String>> {
            songAdapter?.notifyDataSetChanged()
        })

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.itemAnimator = DefaultItemAnimator()
        binding.list.setHasFixedSize(true)
        binding.list.adapter = songAdapter
    }
}

class SongAdapter(private val listData: ArrayList<String>): RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: SongListDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setSongData(data : String){
            binding.songTitle.text = data
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SongListDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        // Create a new view, which defines the UI of the list item
/*        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)*/
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setSongData(listData[position])
    }

    override fun getItemCount() = listData.size
}