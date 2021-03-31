package com.oelrun.english.home

import androidx.lifecycle.*
import com.oelrun.english.database.UnitProgress
import com.oelrun.english.database.UnitProgressDatabaseDao
import kotlinx.coroutines.launch

class HomeViewModel(val database: UnitProgressDatabaseDao): ViewModel() {
    private val _unitProgress =  database.getAll()
    val unitProgress: LiveData<List<UnitProgress>>
        get() = _unitProgress


    private val _navigateToUnitFragment = MutableLiveData<Int>()
    val navigateToUnitFragment: LiveData<Int>
        get() = _navigateToUnitFragment

    var unitName:String = ""

    fun onUnitClicked(unitId: Int) {
        _navigateToUnitFragment.value = unitId
    }

    fun toUnitFragmentNavigated() {
        unitName = ""
        _navigateToUnitFragment.value = null
    }


    fun createBd() {
        val units = listOf("Colors", "Things", "Food", "Cup of tea?", "Outside",
            "Farm", "Zoo", "Can I?", "Clothes", "Fruits", "Veggies")

        viewModelScope.launch {
            for (i in 0..10) {
                val newUnit = UnitProgress(unitName = units[i])
                database.insert(newUnit)
            }
        }
    }

}


class HomeViewModelFactory(private val dataSource: UnitProgressDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

