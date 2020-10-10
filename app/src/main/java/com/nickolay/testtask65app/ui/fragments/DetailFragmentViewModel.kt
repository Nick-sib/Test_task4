package com.nickolay.testtask65app.ui.fragments

import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DetailFragmentViewModel: BaseViewModel<List<String>>() {

    var id: Long? = 0
        set(value) {
            value?.also {
                requestSpecialties(it)
            } ?: run {
                setError(Throwable("EMPTY ID"))
            }
            field = value
        }

    private fun requestSpecialties(id: Long) = launch {
        dataProvider.getUserSpecialties(id).also {
            setData(it)
        }
    }


}