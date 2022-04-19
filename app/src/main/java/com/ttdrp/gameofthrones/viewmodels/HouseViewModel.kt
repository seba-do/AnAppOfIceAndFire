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

        val currentLord = houseBase.currentLord.resolveLord()
        val overlord = houseBase.overlord.resolveHouse()
        val heir = houseBase.heir.resolveLord()
        val cadetBranches = houseBase.cadetBranches.resolveHouse()
        val swornMembers = houseBase.swornMembers.resolveLord()
        val founder = houseBase.founder.resolveLord()

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

    private suspend fun String.resolveLord() = this
        .takeIf { it.isNotBlank() }
        ?.let { (lordRepo.getLord(it.id()) as Result.Success).data }

    private suspend fun String.resolveHouse() = this
        .takeIf { it.isNotBlank() }
        ?.let { (houseRepo.getHouse(it.id()) as Result.Success).data }

    private suspend fun List<String>.resolveHouse() = this
        .takeIf { it.isNotEmpty() }
        ?.map { it.resolveHouse()!! }

    private suspend fun List<String>.resolveLord() = this
        .takeIf { it.isNotEmpty() }
        ?.map { it.resolveLord()!! }
}
