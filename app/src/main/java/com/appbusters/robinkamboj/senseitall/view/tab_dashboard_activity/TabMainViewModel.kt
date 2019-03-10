package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class TabMainViewModel @Inject constructor(): ViewModel() {

    private val _selectedTabLiveData: MutableLiveData<Int> = MutableLiveData()

    val selectedTabLiveData: LiveData<Int>
        get() = _selectedTabLiveData

    fun setSelectedTabIndex(selectedTabIndex: Int) {
        _selectedTabLiveData.postValue(selectedTabIndex)
    }
}