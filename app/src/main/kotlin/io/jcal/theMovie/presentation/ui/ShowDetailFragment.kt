package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentShowDetailBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.ui.ShowDetailFragmentArgs.fromBundle
import io.jcal.theMovie.presentation.viewmodel.TvShowDetailViewModel
import javax.inject.Inject

class ShowDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TvShowDetailViewModel> { viewModelFactory }
    private lateinit var binding: FragmentShowDetailBinding

    private val tvShowId by lazy { fromBundle(arguments!!).showId }

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

        viewModel.getTvShow(tvShowId)

        viewModel.tvShowDetail().observe(viewLifecycleOwner, Observer { show ->
            when (show.state) {
                SUCCESS -> {
                    binding.show = show
                }
            }
        })
    }
}
