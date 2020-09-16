package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.jesusbadenas.kotlin_clean_architecture_project.navigation.Navigator
import org.koin.android.ext.android.inject

/**
 * Base [Fragment] class for every fragment in this application.
 */
abstract class BaseFragment : Fragment() {

    internal val navigator: Navigator by inject()

    fun context(): Context = requireActivity()
}
