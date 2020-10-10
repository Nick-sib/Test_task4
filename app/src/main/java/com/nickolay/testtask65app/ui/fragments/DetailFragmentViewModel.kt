package com.nickolay.testtask65app.ui.fragments

import androidx.lifecycle.ViewModel
import com.nickolay.testtask65app.data.DataAPI
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.launch


class DetailFragmentViewModel: BaseViewModel<List<String>>() {

    var id: Long? = 0
        set(value) {
            value?.also {
                requestSpecialties(it)
            }
            field = value
        }

    private fun requestSpecialties(id: Long) = launch {
        DataAPI.getUserSpecialties(id).also {
            setData(it)
        }
    }


}