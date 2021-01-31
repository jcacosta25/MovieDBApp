package io.jcal.theMovie.presentation.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentShowDetailBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import javax.inject.Inject

class ShowDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TvShowDetailViewModel> { viewModelFactory }
    private lateinit var binding: FragmentShowDetailBinding

    private val args: ShowDetailFragmentArgs by navArgs()

    private val tvShowId by lazy { args.showId }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showPosterImageView.apply {
            transitionName = args.uri
            Picasso.get()
                .load(args.uri)
                .placeholder(R.drawable.background_place_holder)
                .error(R.drawable.background_place_holder)
                .fit()
                .centerCrop()
                .into(this)
        }

        viewModel.getTvShow(tvShowId)

        viewModel.tvShowDetail().observe(
            viewLifecycleOwner,
            Observer { show ->
                when (show.state) {
                    SUCCESS -> {
                        binding.show = show
                    }
                }
            }
        )
    }
}
