package com.malec.simplegenerator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.malec.simplegenerator.R
import com.malec.simplegenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter = NumberAdapter()

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuPrime -> viewModel.setPrime()
            R.id.menuFibonacci -> viewModel.setFibonacci()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        initToolbar()
        initViewModelListeners()
    }

    private fun initRecycler() {
        binding.numberRecycler.adapter = adapter
        binding.numberRecycler.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initToolbar() {
        viewModel.numberType.value?.let {
            binding.toolbar.title = it.name
        }
        setSupportActionBar(binding.toolbar)
    }

    private fun initViewModelListeners() {
        viewModel.numbers.observe(this, {
            adapter.submitList(it)
        })

        viewModel.numberType.observe(this, {
            binding.toolbar.title = it.name
        })
    }
}