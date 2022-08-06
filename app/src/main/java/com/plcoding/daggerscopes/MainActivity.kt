package com.plcoding.daggerscopes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.daggerscopes.ui.theme.DaggerScopesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // The nested graphs are to be able to scope the viewModel into the 2 screens that we require the viewModel to share
    // in this case is the video call one and the call info one
    // This tutorial does not really explains how to create and manage custom scopes, but shortly will be another tuto :)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerScopesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "start_destination") {
                    // this will be our nested graph of the 2 screens
                    navigation(
                        startDestination = "video_call_screen",
                        route = "video_graph"
                    ) {
                        composable("video_call_screen") { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry("video_graph")
                            }
                            // like this to share the same viewModel in the ones in the graph
                            val viewModel = hiltViewModel<SessionViewModel>(parentEntry)
                        }
                        composable("call_info_screen") { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry("video_graph")
                            }
                            // like this to share the same viewModel in the ones in the graph
                            val viewModel = hiltViewModel<SessionViewModel>(parentEntry)
                        }
                    }
                }
            }
        }
    }
}