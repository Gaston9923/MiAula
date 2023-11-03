package com.example.bu_android.di

import android.content.Context
import com.example.miaula.sharedPreferences.SessionPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val SP_FILE = "user_preferences"

@JvmField
val appModule = module {
    single { SessionPreferences(get()) }


//    viewModel { HomeViewModel(get(),get()) }
//    viewModel { RegisterViewModel(get(),get()) }
//    viewModel { LoginViewModel(get(),get()) }
//    viewModel { CourseDetailsHomeViewModel(get(), get()) }
//    viewModel { MyCoursesDetailsViewModel(get(), get()) }
//    viewModel { FaqsViewModel(get()) }
//    viewModel { SettingsViewModel(get(),get()) }
//    viewModel { PasswordViewModel(get()) }
//    viewModel { MyCoursesViewModel(get(),get()) }
//    viewModel { ListMaterialsViewModel(get(),get()) }
//    viewModel { MaterialsFileViewModel(get(),get()) }
//    viewModel { ModulesCourseViewModel(get(),get()) }

    single { androidContext().getSharedPreferences(SP_FILE, Context.MODE_PRIVATE) }
}