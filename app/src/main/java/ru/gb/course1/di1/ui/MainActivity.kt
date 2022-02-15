package ru.gb.course1.di1.ui

import android.os.Bundle
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import ru.gb.course1.di1.app
import ru.gb.course1.di1.databinding.ActivityMainBinding
import ru.gb.course1.di1.domain.NoteEntity
import ru.gb.course1.di1.domain.NoteFactory
import ru.gb.course1.di1.domain.NoteRepo
import javax.inject.Inject

class Presenter(val message: String, val color: Int)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var noteFactory: NoteFactory
    @Inject
    lateinit var noteRepo: NoteRepo
    @Inject
    lateinit var presenter: Presenter
    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app.di.inject(this)

        noteRepo.getNotesLiveData().observe(this) {
            updateResult(it)
        }

        binding.launchActivityButton.setOnClickListener {
            router.openScreen(this)
        }

        binding.addButton.setOnClickListener {
            Thread {
                noteRepo.put(noteFactory.createNewNote())
            }.start()
        }
        binding.clearButton.setOnClickListener {
            Thread {
                noteRepo.clear()
            }.start()
        }

        supportActionBar?.title = presenter.message
        binding.launchActivityButton.setBackgroundColor(presenter.color)
        binding.clearButton.setBackgroundColor(presenter.color)
        binding.addButton.setBackgroundColor(presenter.color)
    }

    @WorkerThread
    private fun updateResult(notes: List<NoteEntity>) {
        val sb = StringBuilder()
        notes.forEach {
            sb.appendLine(it.title)
            sb.appendLine(it.id)
            sb.appendLine()
        }
        runOnUiThread {
            binding.resultTextView.text = sb.toString()
        }
    }

}