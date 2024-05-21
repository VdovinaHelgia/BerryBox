package com.example.berrybox.frag

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.berrybox.ContentActivity
import com.example.berrybox.R
import com.example.berrybox.adapter.FruitAdapter
import com.example.berrybox.adapter.ItemFruit
import com.example.berrybox.databinding.FragmentFavBinding
import com.example.berrybox.db.MainDb

class FavFragment : Fragment(), FruitAdapter.Listener{
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    private val adapter = FruitAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcView.adapter = adapter
    }

    private fun observeData() {
        var isDataHandledParts = true

        val db = MainDb.getDb(requireContext())
        db.getDao().getFavoriteItems().asLiveData().observe(viewLifecycleOwner) {
            if(isDataHandledParts){
                it.forEach { item ->
                    val itemFruit = ItemFruit(
                        item.id,
                        item.nameN,
                        item.description,
                        item.cost,
                        item.avatarUrl,
                        item.favorite,
                        item.shop
                    )
                    adapter.addFruit(itemFruit)

                    if(adapter.itemCount.equals(0))
                        binding.textView11.visibility = View.VISIBLE
                    else
                        binding.textView11.visibility = View.INVISIBLE

                }
                isDataHandledParts = false
            }
        }
    }

    override fun onClick(itemFruit: ItemFruit) {
        // Implement your logic here
        startActivity(Intent(requireActivity(), ContentActivity::class.java).apply {
            putExtra("item", itemFruit)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = FavFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}