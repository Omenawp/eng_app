package com.oelrun.english.unit

import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oelrun.english.R
import com.oelrun.english.database.UnitProgress
import com.oelrun.english.database.UnitProgressDatabaseDao

class UnitViewModel(private val unitId: Int,
                        val database: UnitProgressDatabaseDao): ViewModel() {

    private val unitProgress = MediatorLiveData<UnitProgress>()

    fun getUnitProgress() = unitProgress

    init {
        unitProgress.addSource(database.getUnit(unitId), unitProgress::setValue)
    }
}

@BindingAdapter("isGameCompleted")
fun AppCompatButton.isGameCompleted(isCompleted: Boolean) {
    val background = if(isCompleted)
        R.drawable.main_item_star
    else
        R.drawable.main_item

    setBackgroundResource(background)
}



class UnitViewModelFactory(
        private val unitId: Int,
        private val dataSource: UnitProgressDatabaseDao): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnitViewModel::class.java)) {
            return UnitViewModel(unitId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
