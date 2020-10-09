package com.nickolay.testtask65app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nickolay.testtask65app.R
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.android.synthetic.main.fragment_employees.view.*

class EmployeesFragment: Fragment() {

    //private lateinit var employeesViewModel: EmployeesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //создадим ViewModel



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_employees, container, false)
        //root.section_label.text = arguments?.getLong(ARG_SECTION_NUMBER).toString() ?: "err"

        this.context?.let {

            root.recyclerView.adapter = EmployeesAdapter {
                    Log.d("myLOG", "click ${it.f_name}: ")
                }
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