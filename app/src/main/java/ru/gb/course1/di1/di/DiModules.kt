package ru.gb.course1.di1.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gb.course1.di1.data.RandomNoteFactoryImpl
import ru.gb.course1.di1.data.room.NoteDb
import ru.gb.course1.di1.data.room.RoomNoteRepoImpl
import ru.gb.course1.di1.domain.NoteFactory
import ru.gb.course1.di1.domain.NoteRepo
import ru.gb.course1.di1.ui.Presenter
import java.util.*

const val UUID_QUALIFIER = "uuid"
const val DB_NAME_QUALIFIER = "dbname"

@Module
@ComponentScan("ru.gb.course1.di1")
class AnnotationModule

val appModule = module {
    single<NoteFactory> { RandomNoteFactoryImpl(get()) }
    factory(named(UUID_QUALIFIER)) { UUID.randomUUID().toString() }
    viewModel { Presenter(get(named(UUID_QUALIFIER)), get()) }
    factory { Random().nextInt() }
}

val dbModule = module {
    factory(named(DB_NAME_QUALIFIER)) { "dbname-sdfvs-dv-sdv-sdv--sdv-d" }
    single {
        Room.databaseBuilder(
            get(),
            NoteDb::class.java,
            get(named(DB_NAME_QUALIFIER))
        ).build()
    }
    single { get<NoteDb>().noteDao() }
    single<NoteRepo> { RoomNoteRepoImpl(get()) }
}
