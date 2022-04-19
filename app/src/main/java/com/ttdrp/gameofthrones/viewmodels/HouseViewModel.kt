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
class HouseViewModel @Inject constructor(
    private val houseRepo: HouseRepository,
    private val lordRepo: LordRepository
) : ViewModel() {

    suspend fun getResolvedHouse(id: String) = try {
        val houseBase = (houseRepo.getHouse(id) as Result.Success).data

        val currentLord = houseBase.currentLord
            .takeIf { it.isNotBlank() }
            ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

        val overlord = houseBase.overlord
            .takeIf { it.isNotBlank() }
            ?.let { (houseRepo.getHouse(it.id()) as Result.Success).data }

        val heir = houseBase.heir
            .takeIf { it.isNotBlank() }
            ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

        val cadetBranches = houseBase.cadetBranches
            .takeIf { it.isNotEmpty() }
            ?.map {
                (houseRepo.getHouse(it.id()) as Result.Success).data
            }

        val swornMembers = houseBase.swornMembers
            .takeIf { it.isNotEmpty() }
            ?.map {
                (lordRepo.getLord(it.id()) as Result.Success).data
            }

        val founder = houseBase.founder
            .takeIf { it.isNotBlank() }
            ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

        Result.Success(
            houseBase.toResolved(
                currentLord = currentLord,
                overlord = overlord,
                heir = heir,
                cadetBranches = cadetBranches,
                swornMembers = swornMembers,
                founder = founder
            )
        )
    } catch (e: Exception) {
        Result.Error(e)
    }
}