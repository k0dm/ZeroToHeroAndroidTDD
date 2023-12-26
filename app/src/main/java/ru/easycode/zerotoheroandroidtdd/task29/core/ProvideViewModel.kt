package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.create.CreateFolderViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.edit.EditFolderViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.task29.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.task29.note.create.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.task29.note.edit.EditNoteViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clasz: Class<T>): T

    class Base(private val core: Core) : ProvideViewModel {
        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            val viewModel: ViewModel = when (clasz) {

                MainViewModel::class.java -> MainViewModel(core.navigation())

                FolderListViewModel::class.java -> FolderListViewModel(
                    core.foldersRepository(),
                    core.folderListLiveDataWrapper(),
                    core.folderLiveDataWrapper(),
                    core.navigation()
                )

                CreateFolderViewModel::class.java -> CreateFolderViewModel(
                    core.foldersRepository(),
                    core.folderListLiveDataWrapper(),
                    core.navigation(),
                    core.clear()
                )

                FolderDetailsViewModel::class.java -> FolderDetailsViewModel(
                    core.notesRepository(),
                    core.noteListLiveDataWrapper(),
                    core.folderLiveDataWrapper(),
                    core.navigation(),
                    core.clear()
                )

                EditFolderViewModel::class.java -> EditFolderViewModel(
                    core.folderLiveDataWrapper(),
                    core.foldersRepository(),
                    core.navigation(),
                    core.clear()
                )

                CreateNoteViewModel::class.java -> CreateNoteViewModel(
                    core.folderLiveDataWrapper(),
                    core.noteListLiveDataWrapper(),
                    core.notesRepository(),
                    core.navigation(),
                    core.clear()
                )

                EditNoteViewModel::class.java -> EditNoteViewModel(
                    core.folderLiveDataWrapper(),
                    core.noteLiveDataWrapper(),
                    core.noteListLiveDataWrapper(),
                    core.notesRepository(),
                    core.navigation(),
                    core.clear()
                )

                else -> throw IllegalStateException("No such ViewModel with class: ${clasz.name}")
            }
            return viewModel as T
        }
    }

    class Factory(
        private val provideViewModel: ProvideViewModel,
    ) : ProvideViewModel, ClearViewModels {

        private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            val viewModel = viewModelMap[clasz]
            return if (viewModelMap[clasz] == null) {
                provideViewModel.viewModel(clasz).apply {
                    viewModelMap[clasz] = this
                }
            } else {
                viewModel as T
            }
        }

        override fun clear(vararg viewModelClasses: Class<out ViewModel>) =
            viewModelClasses.forEach { viewModelMap.remove(it) }
    }
}