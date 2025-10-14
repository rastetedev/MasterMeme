package com.raulastete.mastermeme.core

import com.raulastete.mastermeme.presentation.feature.create_meme.MemeTextState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MemeTextUndoRedoManager(private val maxHistorySize: Int = 5) {

    private val undoStack = mutableListOf<MemeTextState>()
    private val redoStack = mutableListOf<MemeTextState>()

    private val _canUndo = MutableStateFlow(false)
    val canUndo: StateFlow<Boolean> = _canUndo.asStateFlow()

    private val _canRedo = MutableStateFlow(false)
    val canRedo: StateFlow<Boolean> = _canRedo.asStateFlow()

    private fun updateState() {
        _canUndo.update { undoStack.isNotEmpty() }
        _canRedo.update { redoStack.isNotEmpty() }
    }

    fun addNewChange(state: MemeTextState) {
        undoStack.add(state)

        if (undoStack.size > maxHistorySize) {
            undoStack.removeAt(0)
        }

        updateState()
    }

    fun undo(): MemeTextState? {
        if (undoStack.isEmpty()) return null

        val lastState = undoStack.removeAt(undoStack.lastIndex)
        redoStack.add(lastState)

        updateState()

        return lastState
    }

    fun redo(): MemeTextState? {
        if (redoStack.isEmpty()) return null

        val stateToRedo = redoStack.removeAt(redoStack.lastIndex)
        undoStack.add(stateToRedo)

        updateState()

        return stateToRedo
    }
}