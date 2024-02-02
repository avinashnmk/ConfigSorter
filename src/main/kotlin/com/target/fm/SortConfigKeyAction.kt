package com.target.fm

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.TextRange

class SortConfigKeyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return

        val selectedTextEditor = FileEditorManager.getInstance(project).selectedTextEditor ?: return

        // Check if the file has the "*.conf" extension
        val document = selectedTextEditor.document
        val file = FileDocumentManager.getInstance().getFile(document)
        if (file == null || !file.name.endsWith(".conf", ignoreCase = true)) {
            Messages.showMessageDialog(
                "Please open a *.conf file to perform this action.",
                "Error",
                Messages.getErrorIcon(),
            )
            return
        }

        // Get the selected text (assuming it's the configuration in text form)
        val hasSelection = selectedTextEditor.selectionModel.hasSelection()
        val selectionStart = if (hasSelection) selectedTextEditor.selectionModel.selectionStart else 0
        val selectionEnd = if (hasSelection) selectedTextEditor.selectionModel.selectionEnd else document.textLength
        val selectedText = document.getText(TextRange(selectionStart, selectionEnd))

        // Use WriteCommandAction to perform the modification
        WriteCommandAction.runWriteCommandAction(project) {
            // Replace the selected text with the sorted configuration
            document.replaceString(
                selectionStart,
                selectionEnd,
                sortConfigBlocks(selectedText),
            )
        }
    }
}
