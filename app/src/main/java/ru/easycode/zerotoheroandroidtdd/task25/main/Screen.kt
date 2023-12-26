package ru.easycode.zerotoheroandroidtdd.task25.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

interface Screen {

    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(private val fragmentClass: Class<out Fragment>) : Screen {

        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
            }
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {

        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                addToBackStack(fragmentClass.name)
            }
        }
    }

    object Pop : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.popBackStack()
        }
    }
}


