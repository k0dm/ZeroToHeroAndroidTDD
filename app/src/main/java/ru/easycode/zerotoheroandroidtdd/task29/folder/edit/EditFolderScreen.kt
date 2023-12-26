package ru.easycode.zerotoheroandroidtdd.task29.folder.edit

import ru.easycode.zerotoheroandroidtdd.task29.main.Screen

data class EditFolderScreen(private val folderId: Long, private val folderName: String) :
    Screen.Replace(EditFolderFragment::class.java, EditFolderFragment.bundle(folderId, folderName))