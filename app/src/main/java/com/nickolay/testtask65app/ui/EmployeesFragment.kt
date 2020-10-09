package com.nickolay.testtask65app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nickolay.testtask65app.R
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.android.synthetic.main.fragment_employees.view.*

class EmployeesFragment: Fragment() {

    //private lateinit var employeesViewModel: EmployeesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_employees, container, false)
        root.section_label.text = arguments?.getString(ARG_SECTION_NUMBER) ?: "err"
        return root
    }


    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: String): EmployeesFragment {
            return EmployeesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}