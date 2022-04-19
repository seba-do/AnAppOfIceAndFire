package com.ttdrp.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.HouseRepository
import com.ttdrp.gameofthrones.data.lord.LordRepository
import com.ttdrp.gameofthrones.model.toResolved
import com.ttdrp.gameofthrones.utils.id
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LordViewModel @Inject constructor(
    private val houseRepo: HouseRepository,
    private val lordRepo: LordRepository
) : ViewModel() {

    suspend fun getResolvedLord(name: String) = try {
        val lordBase = (lordRepo.getLord(name) as Result.Success).data

        val father = lordBase.father
            .takeIf { it.isNotBlank() }
            ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

        val mother = lordBase.mother
            .takeIf { it.isNotBlank() }
            ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

        val spouse = lordBase.spouse
            .takeIf { it.isNotBlank() }
            ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

        val allegiances = lordBase.allegiances
            .takeIf { it.isNotEmpty() }
            ?.map {
                (houseRepo.getHouse(it.id()) as Result.Success).data
            }

        Result.Success(
            lordBase.toResolved(
                father = father,
                mother = mother,
                spouse = spouse,
                allegiances = allegiances
            )
        )
    } catch (e: Exception) {
        Result.Error(e)
    }

}