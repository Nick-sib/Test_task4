package com.nickolay.testtask65app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.entity.Employees
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.ui.adapters.EmployeesAdapter
import com.nickolay.testtask65app.ui.adapters.SpecialtysAdapter
import com.nickolay.testtask65app.ui.base.BaseFragment
import com.nickolay.testtask65app.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.android.synthetic.main.fragment_employees.view.*
import kotlinx.android.synthetic.main.fragment_specialtys.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EmployeesFragment: BaseFragment<List<EmployeesModel>>() {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    override val viewModel: FragmentViewModel by lazy {
        ViewModelProvider(this).get(FragmentViewModel::class.java)
    }

    override fun renderData(data: List<EmployeesModel>) {
        (recyclerView.adapter as EmployeesAdapter).data = data
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = arguments?.getLong(EXTRA_DATA)
        id?.apply {
            viewModel.id = this
        }

        val root = inflater.inflate(R.layout.fragment_employees, container, false)
        this.context?.let {
            root.recyclerView.adapter = EmployeesAdapter {
                    Log.d("myLOG", "click ${it.f_name}: ")
                }
        }
        return root
    }

    companion object {
        private val EXTRA_DATA = SpecialtysFragment::class.java.name + "extra.DATA"

        @JvmStatic
        fun newInstance(specialtyId: Long) =
            EmployeesFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_DATA, specialtyId)
                }
            }
    }

}