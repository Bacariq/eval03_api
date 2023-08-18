package com.technipixl.exo1.ComicsDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technipixl.exo1.Comics.AdapterComics
import com.technipixl.exo1.Comics.ComicsFragmentArgs
import com.technipixl.exo1.Models.ApiResponse
import com.technipixl.exo1.Models.Comic
import com.technipixl.exo1.Models.Item
import com.technipixl.exo1.Models.MarvelResponse
import com.technipixl.exo1.Service.HttpServiceImp
import com.technipixl.exo1.databinding.FragmentComicsBinding
import com.technipixl.exo1.databinding.FragmentComicsDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsDetailFragment : Fragment() {

    private var binding: FragmentComicsDetailBinding? = null
    private val args: ComicsDetailFragmentArgs by navArgs()
    private val httpService by lazy { HttpServiceImp() }
    private var comicRessourceId : String = ""
    private var heroName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comicRessourceId = args.comicsId
        heroName = args.heroName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsDetailBinding.inflate(layoutInflater, container, false)
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

    private fun extractIdFromUrl(url: String): Long {
        val segments = url.split("/")
        val lastSegment = segments.lastOrNull()
        return lastSegment?.toLongOrNull() ?: -1L
    }

    private fun retrieveData() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpService.getComicDetail(extractIdFromUrl(comicRessourceId))
            withContext(Dispatchers.Main) {
                response.body()?.let { body ->
                    TraiterResponse(Response = body)
                }
            }
        }
    }

    private fun TraiterResponse(Response: MarvelResponse){
        if(binding != null){
            var mResponse : MarvelResponse = Response
            binding!!.ComicDetailTitre.setText(heroName + " - " + mResponse.data.results[0].title)
            binding!!.ComicDetailText.setText(mResponse.data.results[0].description)
            Glide.with(this)
                .load(mResponse.data.results[0].thumbnail.path + "." + mResponse.data.results[0].thumbnail.extension)
                .into(binding!!.ComicDetailImage)
        }
    }

}