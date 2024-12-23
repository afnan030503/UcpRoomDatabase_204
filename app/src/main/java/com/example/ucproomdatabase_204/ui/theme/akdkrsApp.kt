package com.example.ucproomdatabase_204.ui.theme

import android.app.Application
import com.example.ucproomdatabase_204.dependeciesinjection.ContainerApp
import com.example.ucproomdatabase_204.dependeciesinjection.InterfaceContainerApp

class akdkrsApp: Application() {
    lateinit var containerApp: ContainerApp

    override  fun onCreate(){
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}
