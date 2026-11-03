package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.screens.NgoAppRoot

class MainActivity : ComponentActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         val TAG = "MainActivityTag"

        Log.d(TAG, "Este es un mensaje de depuración (Debug)")
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            NgoAppRoot(viewModel = viewModel)
        }
    }
}
