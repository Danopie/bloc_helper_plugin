package com.danopie

import com.danopie.generator.base.BlocGeneratorFactory
import com.danopie.generator.base.Generator
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.*

class CreateBlocAction: AnAction("Create Bloc"), Listener {
    private lateinit var dataContext: DataContext

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    override fun onCreateClicked(name: String) {
        name?.let { name ->
            val generators = BlocGeneratorFactory.getBlocGenerators(name, false)
            generate(generators)
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = CreateBlocDialog(this);
        dialog.show();
    }

    protected fun generate(mainSourceGenerators: List<Generator>) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory

        ApplicationManager.getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                project,
                {
                    mainSourceGenerators.forEach { createSourceFile(project!!, it, directory!!) }
                },
                "Generate a new Bloc",
                null
            )
        }
    }

    private fun createSourceFile(project: Project, generator: Generator, directory: PsiDirectory) {
        val fileName = generator.fileName()
        val existingPsiFile = directory.findFile(fileName)
        val path = getPath(directory)
        if (existingPsiFile != null) {
            val document = PsiDocumentManager.getInstance(project).getDocument(existingPsiFile)
            document?.insertString(document.textLength, "\n" + generator.generate(path))
            return
        }
        val psiFile = PsiFileFactory.getInstance(project)
            .createFileFromText(fileName, JavaLanguage.INSTANCE, generator.generate(path))
        directory.add(psiFile)
    }

    private fun getPath(directory: PsiDirectory?): String{
        val path = mutableListOf<String?>()
        var d = directory
        do {
            path.add(d?.name)
            d = d?.parent
        } while (d != null)

        val libIndex = path.indexOf("lib")
        return path.subList(0, libIndex).reversed().joinToString("/")

    }

}