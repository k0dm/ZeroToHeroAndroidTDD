package ru.easycode.zerotoheroandroidtdd.task29.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(fragmentManager: FragmentManager, containerId: Int)

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {

        override fun show(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }

    abstract class Replace(
        private val fragmentClass: Class<out Fragment>,
        private val bundle: Bundle? = null,
    ) : Screen {

        override fun show(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, fragmentClass, bundle)
                .commit()
        }
    }

}