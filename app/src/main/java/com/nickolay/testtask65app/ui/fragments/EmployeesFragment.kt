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
import com.nickolay.testtask65app.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.android.synthetic.main.fragment_employees.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EmployeesFragment: Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    private lateinit var dataEmployees: Job

    val viewModel: FragmentViewModel by lazy {
        ViewModelProvider(this).get(FragmentViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        dataEmployees = launch {
            viewModel.getData().consumeEach {
                //(recyclerView.adapter as EmployeesAdapter).data = it

                Log.d("myLOG", "$it")
            }
        }
    }

    override fun onStop() {
        dataEmployees.cancel()
        super.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_employees, container, false)

        val id = arguments?.getLong(ARG_SECTION_NUMBER)
        id?.apply {
            viewModel.id = this
        }

//        id?.also {
//            viewModel.id = it
//        }
//        arguments?.let {
//            viewModel.id = it.getLong(ARG_SECTION_NUMBER)
//        }
         //=  ?: 0L
        //root.section_label.text = arguments?.getLong(ARG_SECTION_NUMBER).toString() ?: "err"

        this.context?.let {

            root.recyclerView.adapter = EmployeesAdapter {
                    Log.d("myLOG", "click ${it.f_name}: ")
                }

            val dd =  EmployeesModel(0,id.toString(),"0","0","0")

//            var rr = listOf(dd,dd,dd,dd,dd,dd,dd,dd,dd,dd,dd)
//            (root.recyclerView.adapter as EmployeesAdapter).data = rr
//            for(i in 1..100){
//                rr.add()
//            }
        }
        return root
    }


    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(specialtyId: Long): EmployeesFragment {
            return EmployeesFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_SECTION_NUMBER, specialtyId)
                }
            }
        }
    }
}