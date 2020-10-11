package com.nickolay.testtask65app.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.ui.adapters.EmployeesAdapter
import com.nickolay.testtask65app.ui.base.BaseFragment
import com.nickolay.testtask65app.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.android.synthetic.main.fragment_employees.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class EmployeesFragment: BaseFragment<List<EmployeesModel>>() {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }


    override val viewModel: EmployeesViewModel by lazy {
        ViewModelProvider(this).get(EmployeesViewModel::class.java)
    }

    override fun renderData(data: List<EmployeesModel>) {
        (recyclerView.adapter as EmployeesAdapter).data = data
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root =inflater.inflate(R.layout.fragment_employees, container, false)?.apply {

            recyclerView.adapter = EmployeesAdapter{
                MainActivity.instance.supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        DetailEmployeesFragment.newInstance(it)
                    )
                    .commit()
                //MainActivity.instance.showFragment(fragment =  DetailEmployeesFragment.newInstance(it))
            }

//            val dd = EmployeesModel(1,"1","1","1","1")
//            (recyclerView.adapter as EmployeesAdapter).data = listOf(dd,dd,dd,dd,dd,dd,dd,dd,dd,dd)

            bbutton.setOnClickListener {
                Log.d("myLOG", (recyclerView.adapter as EmployeesAdapter).data.toString())


            }
        }

        val id = arguments?.getLong(EXTRA_DATA)
        id?.apply {
            viewModel.id = this
        }



        return root
    }



    companion object {
        private val EXTRA_DATA = EmployeesFragment::class.java.name + "extra.DATA"

        @JvmStatic
        fun newInstance(specialtyId: Long) =
            EmployeesFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_DATA, specialtyId)
                }
            }
    }

}