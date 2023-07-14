package com.code.suitmedia.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.suitmedia.R
import com.code.suitmedia.adapter.LoadingStateAdapter
import com.code.suitmedia.adapter.UserListAdapter
import com.code.suitmedia.data.resource.remote.response.DataItem
import com.code.suitmedia.databinding.ActivityThirdBinding
import com.code.suitmedia.second.SecondActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private val thirdViewModel: ThirdViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
            title = "Third Screen"
        }
        showRecyclerList()

        thirdViewModel.getDataUser()
        getData()

    }


    private fun getData() {
        val adapter = UserListAdapter(object : UserListAdapter.OnClickListener {
            override fun onItemClick(data: DataItem) {
                val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                intent.putExtra(SecondActivity.SELECTED_USER_NAME, "${data.firstName} ${data.lastName}")
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        })
        binding.viewCard.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        thirdViewModel.dataUser.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(this)
        binding.viewCard.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.viewCard.addItemDecoration(itemDecoration)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}