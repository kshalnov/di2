package ru.gb.course1.di1.di

import dagger.Module
import dagger.Provides
import ru.gb.course1.di1.ui.Presenter
import java.util.*
import javax.inject.Named

@Module
class MvpModule {

    @Provides
    fun providePresenter(@Named("uuid") uuid: String): Presenter {
        return Presenter(uuid, Random().nextInt())
    }
}