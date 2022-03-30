package com.noranekoit.made.submission1.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.noranekoit.made.submission1.R
import com.noranekoit.made.submission1.core.ui.MovieAdapter
import com.noranekoit.made.submission1.core.ui.ViewModelFactory
import com.noranekoit.made.submission1.databinding.FragmentFavoriteBinding
import com.noranekoit.made.submission1.detail.DetailMovieActivity

class FavoriteFragment : Fragment() {
    private lateinit var favoriteViewModel: FavoriteViewModel

    private var _binding: FragmentFavoriteBinding?= null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                selectedData ->
                val intent = Intent(activity,DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this,factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner) { dataMovie ->
                movieAdapter.setData(dataMovie)
                binding.viewEmpty.root.visibility =
                    if (dataMovie.isEmpty()) View.VISIBLE else View.GONE
            }

            with(binding.rvMovie){
                layoutManager =LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}