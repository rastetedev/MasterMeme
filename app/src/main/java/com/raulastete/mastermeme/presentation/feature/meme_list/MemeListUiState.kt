package com.raulastete.mastermeme.presentation.feature.meme_list

import com.raulastete.mastermeme.R

data class MemeListUiState(
    val memes: List<MemeUiState> = emptyList(),
    val isSelectionMode: Boolean = false,
    val modalState: ModalState = ModalState(),
) {
    val selectedMemes = memes.filter { it.isSelected }
}

data class ModalState(
    val isOpen: Boolean = false,
    val isSearchMode: Boolean = false,
    val query: String = "",
    val templates: List<Int> = memeTemplateResourceList
)

data class MemeUiState(
    val id: String = "",
    val image: String = "",
    val isFavorite: Boolean = false,
    val isSelected: Boolean = false,
)

val memeTemplateResourceList = listOf(
    R.drawable.benaffleck,
    R.drawable.bobsponjareliable,
    R.drawable.bobsponjaevolution,
    R.drawable.brainawake,
    R.drawable.changemymind,
    R.drawable.clownmakeup,
    R.drawable.disastergirl,
    R.drawable.drake,
    R.drawable.epichandshake,
    R.drawable.excited,
    R.drawable.grusplan,
    R.drawable.headaches,
    R.drawable.hidethepainharold,
    R.drawable.isthisapigeon,
    R.drawable.jacksparrowbeingchased,
    R.drawable.leftexitofframp,
    R.drawable.leonardodicapriocheers,
    R.drawable.mirror,
    R.drawable.mrbean,
    R.drawable.mrincreible,
    R.drawable.mrsimons,
    R.drawable.office,
    R.drawable.photographhandshake,
    R.drawable.runningawayballoon,
    R.drawable.sadpabloescobar,
    R.drawable.scaredcat,
    R.drawable.skeleton,
    R.drawable.spiderman,
    R.drawable.tape,
    R.drawable.therockdriving,
    R.drawable.thirdworldskepticalkid,
    R.drawable.thinkingaboutotherwomen,
    R.drawable.todolist,
    R.drawable.tornado,
    R.drawable.twobuttons,
    R.drawable.whatwewant
)
