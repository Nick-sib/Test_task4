package com.nickolay.testtask65app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask65app.R
import kotlinx.android.synthetic.main.fragment_detail_employees.view.*


//стоит вынести часть функционала в BaseFragment
class DetailEmployeesFragment : Fragment() {

    val viewModel: DetailFragmentViewModel by lazy {
        ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail_employees, container, false)

        val id = arguments?.getLong(ARG_SECTION_NUMBER)
        id?.apply {
            viewModel.id = this
            root.tv.text = this.toString()
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