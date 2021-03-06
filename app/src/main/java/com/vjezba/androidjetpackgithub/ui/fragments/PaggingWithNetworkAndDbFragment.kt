package com.vjezba.androidjetpackgithub.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vjezba.androidjetpackgithub.databinding.FragmentPaggingNetworkAndDbBinding
import com.vjezba.androidjetpackgithub.di.Injectable
import com.vjezba.androidjetpackgithub.di.ViewModelFactory
import com.vjezba.androidjetpackgithub.di.injectViewModel
import com.vjezba.androidjetpackgithub.ui.activities.LanguagesActivity
import com.vjezba.androidjetpackgithub.ui.dialog.ChooseProgrammingLanguageDialog
import com.vjezba.androidjetpackgithub.viewmodels.LanguagesListViewModel
import com.vjezba.androidjetpackgithub.viewmodels.PaggingWithNetworkAndDbViewModel
import kotlinx.android.synthetic.main.activity_languages_main.*
import kotlinx.android.synthetic.main.fragment_pagging_network_and_db.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val UPDATE_PERIOD = 10000L

class PaggingWithNetworkAndDbFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PaggingWithNetworkAndDbViewModel

    private var automaticIncreaseNumberByOne: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentPaggingNetworkAndDbBinding.inflate(inflater, container, false)
        context ?: return binding.root

        viewModel.incrementNumberAutomaticallyByOne.observe(viewLifecycleOwner, Observer { currentNumber ->
            tvNumberIncreaseAutomatically.text = "" + currentNumber
        })

        viewModel.incrementNumberManuallyByOne.observe(viewLifecycleOwner, Observer { currentNumber ->
            tvNumberIncreaseManually.text = "" + currentNumber
        })


        activity?.speedDial?.visibility = View.GONE
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        btnIncreaseNumber.setOnClickListener {
            viewModel.incrementManuallyByOne()
        }

        btnChooseLanguage.setOnClickListener {
            val chooseProgrammingLanguageDialog =
                ChooseProgrammingLanguageDialog(automaticIncreaseNumberByOne)
            chooseProgrammingLanguageDialog.show(
                (requireActivity() as LanguagesActivity).supportFragmentManager,
                "")
        }

        automaticIncreaseNumberByOne?.cancel()
        automaticIncreaseNumberByOne =
            lifecycleScope.launch {
            while (true) {
                delay(UPDATE_PERIOD)
                try {
                    handleUpdate()
                } catch (ex: Exception) {
                    Log.v("ERROR","Periodic remote-update failed...", ex)
                }
            }
        }
    }

    private fun handleUpdate() {
        viewModel.incrementAutomaticallyByOne()
    }

}