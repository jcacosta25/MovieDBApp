package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentShowDetailBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.ui.ShowDetailFragmentArgs.fromBundle
import io.jcal.theMovie.presentation.viewmodel.TvShowDetailViewModel
import javax.inject.Inject

class ShowDetailFragment : DaggerFragment() {

    private lateinit var viewModel: TvShowDetailViewModel
    private lateinit var binding: FragmentShowDetailBinding

    private val tvShowId by lazy { fromBundle(arguments!!).showId }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TvShowDetailViewModel::class.java)

        viewModel.getTvShow(tvShowId)

        viewModel.tvShowDetail().observe(this, Observer { show ->
            when (show.state) {
                SUCCESS -> {
                    binding.show = show
                }
            }
        })
    }
}