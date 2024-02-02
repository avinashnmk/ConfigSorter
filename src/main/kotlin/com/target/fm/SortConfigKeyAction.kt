package com.target.configSorter

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class SortConfigKeyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return

        val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: return

        // Get the selected text (assuming it's the configuration in text form)
        val selectedText = editor.selectionModel.selectedText
        if (selectedText.isNullOrEmpty()) {
            Messages.showMessageDialog("Please select the configuration to sort.", "Error", Messages.getErrorIcon())
            return
        }

        // Implement the sorting logic (modify as needed)
        val sortedConfig = sortConfigKeys(selectedText)

        // Replace the selected text with the sorted configuration
        editor.document.replaceString(
            editor.selectionModel.selectionStart,
            editor.selectionModel.selectionEnd,
            sortedConfig,
        )
    }

    private fun sortConfigKeys(config: String): String {
        // Implement the sorting logic for the configuration
        // Parse the configuration, sort keys, and return the sorted configuration
        return config // Placeholder, replace with actual sorting logic
    }
}
