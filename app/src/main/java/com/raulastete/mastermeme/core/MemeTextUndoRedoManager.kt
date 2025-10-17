package com.raulastete.mastermeme.core

import com.raulastete.mastermeme.presentation.model.MemeText

class MemeTextUndoRedoManager(private val maxHistorySize: Int = 5) {

    private val undoStack = mutableListOf<MemeText>()
    private val redoStack = mutableListOf<MemeText>()

    val canUndo = undoStack.isNotEmpty()
    val canRedo =  redoStack.isNotEmpty()

    fun addNewChange(state: MemeText) {
        undoStack.add(state)

        if (undoStack.size > maxHistorySize) {
            undoStack.removeAt(0)
        }
    }

    fun undo(): MemeText? {
        if (undoStack.isEmpty()) return null

        val lastState = undoStack.removeAt(undoStack.lastIndex)
        redoStack.add(lastState)

        return lastState
    }

    fun redo(): MemeText? {
        if (redoStack.isEmpty()) return null

        val stateToRedo = redoStack.removeAt(redoStack.lastIndex)
        undoStack.add(stateToRedo)

        return stateToRedo
    }
}