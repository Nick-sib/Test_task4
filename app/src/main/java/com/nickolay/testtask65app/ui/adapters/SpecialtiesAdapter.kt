package com.nickolay.testtask65app.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.fragments.EmployeesFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SpecialtiesAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var specialties: List<SpecialtyModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount() = specialties.size

    @ExperimentalCoroutinesApi
    override fun getItem(position: Int): Fragment {
        return EmployeesFragment.newInstance(specialties[position].specialtyId)
    }

    override fun getPageTitle(position: Int) = specialties[position].specialtyName

}