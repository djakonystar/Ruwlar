package dev.djakonystar.ruwlar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import dev.djakonystar.ruwlar.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val adapter by lazy { RuwlarAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        sharedPreferences = requireActivity().getSharedPreferences("Ruwlar", Context.MODE_PRIVATE)

        binding.recyclerView.adapter = adapter

        binding.etSearch.addTextChangedListener {
            val searchValue = it.toString()
            if (searchValue.isNotBlank()) {
                Provider.searchRuw("%$searchValue%")
            } else {
                Provider.getNewRuwlar(requireContext(), 0)
            }
        }

        Provider.getNewRuwlar(requireContext(), 0)

        adapter.setOnItemClickListener {
            Provider.getNewRuwlar(requireContext(), it.id)
        }

        Provider.newList.observe(viewLifecycleOwner) {
            adapter.models = it

            it.firstOrNull()?.let {
                sharedPreferences.edit().putInt(Keys.PARENT_ID, it.parentId).apply()
            }
        }
    }
}
