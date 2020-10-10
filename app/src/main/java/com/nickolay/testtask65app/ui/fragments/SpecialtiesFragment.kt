package com.nickolay.testtask65app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.adapters.SpecialtiesAdapter
import com.nickolay.testtask65app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_specialtys.*
import kotlinx.android.synthetic.main.fragment_specialtys.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class SpecialtiesFragment: BaseFragment<List<SpecialtyModel>>() {

    override val viewModel: SpecialtiesViewModel by lazy {
        ViewModelProvider(this).get(SpecialtiesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?)
            = inflater.inflate( R.layout.fragment_specialtys, container, false)?.also {
        it.view_pager.adapter = SpecialtiesAdapter(parentFragmentManager)

    }

    override fun renderData(data: List<SpecialtyModel>) {
        (view_pager.adapter as SpecialtiesAdapter).specialtys = data
    }

    companion object {

       //private val EXTRA_DATA = SpecialtiesFragment::class.java.name + "extra.DATA"

       @JvmStatic
        fun newInstance() = SpecialtiesFragment()
    }

}