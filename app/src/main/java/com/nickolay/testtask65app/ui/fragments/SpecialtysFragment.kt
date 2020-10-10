package com.nickolay.testtask65app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.adapters.SpecialtysAdapter
import com.nickolay.testtask65app.ui.base.BaseFragment
import com.nickolay.testtask65app.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_specialtys.*
import kotlinx.android.synthetic.main.fragment_specialtys.view.*

class SpecialtysFragment: BaseFragment<List<SpecialtyModel>>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate( R.layout.fragment_specialtys, container, false).also {
        it.view_pager.adapter = SpecialtysAdapter(parentFragmentManager)
    }

    override fun renderData(data: List<SpecialtyModel>) {
        (view_pager.adapter as SpecialtysAdapter).specialtys = data
    }

    companion object {

       // private val EXTRA_DATA = SpecialtysFragment::class.java.name + "extra.DATA"

       @JvmStatic
        fun newInstance() = SpecialtysFragment()
    }

}