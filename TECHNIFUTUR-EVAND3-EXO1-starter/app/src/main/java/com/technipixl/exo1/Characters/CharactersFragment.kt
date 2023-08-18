package com.technipixl.exo1.Characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.Models.ApiResponse
import com.technipixl.exo1.Models.Character
import com.technipixl.exo1.Service.HttpServiceImp
import com.technipixl.exo1.databinding.FragmentCharactersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersFragment : Fragment(), AdapterCharacters.OnLikeClickListener  {

    private var binding: FragmentCharactersBinding? = null
    private val httpService by lazy { HttpServiceImp() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        retrieveData()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    private fun retrieveData() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = httpService.getCharactersList()

            withContext(Dispatchers.Main) {
                response.body()?.let { body ->
                    setupRecycler(Response = body)
                }
            }
        }
    }

    private fun setupRecycler(Response: ApiResponse) {
        val recyclerView = binding!!.CharactersRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView?.adapter = AdapterCharacters(Response.data.results.toMutableList(), this)
    }

    override fun ItemOnClick(cellItem: Character) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToComicsFragment(cellItem.id)
        findNavController().navigate(action)
    }

}