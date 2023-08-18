package com.technipixl.exo1.Comics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technipixl.exo1.Characters.AdapterCharacters
import com.technipixl.exo1.Characters.CharactersFragmentDirections
import com.technipixl.exo1.Models.ApiResponse
import com.technipixl.exo1.Models.Character
import com.technipixl.exo1.Models.Comic
import com.technipixl.exo1.Models.Item
import com.technipixl.exo1.databinding.FragmentComicsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.technipixl.exo1.Service.HttpServiceImp
import com.technipixl.exo1.databinding.FragmentCharactersBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ComicsFragment : Fragment(), AdapterComics.OnLikeClickListener  {

    private var binding: FragmentComicsBinding? = null
    private val httpService by lazy { HttpServiceImp() }
    private val args: ComicsFragmentArgs by navArgs()
    private var characterId : Long = 0
    private var heroName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterId = args.characterId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun retrieveData() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpService.getComics(characterId)
            withContext(Dispatchers.Main) {
                response.body()?.let { body ->
                    TraiterResponse(Response = body)
                }
            }
        }
    }

    private fun TraiterResponse(Response: ApiResponse){
        if(binding != null){
            var mResponse : ApiResponse = Response

            heroName = mResponse.data.results[0].name
            binding!!.ComicsName.setText(heroName)

            Glide.with(this)
                .load(mResponse.data.results[0].thumbnail.path + "." + mResponse.data.results[0].thumbnail.extension)
                .into(binding!!.ComicsImage)

            setupRecycler(mResponse.data.results[0].comics )

        }
    }

    private fun setupRecycler(pComics: Comic) {
        val recyclerView = binding!!.ComicsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = AdapterComics(pComics.items.toMutableList(), this)
    }

    override fun ItemOnClick(cellItem: Item) {
        val action =
            ComicsFragmentDirections.actionComicsFragmentToComicsDetailFragment(cellItem.resourceURI,heroName)
        findNavController().navigate(action)
    }
}