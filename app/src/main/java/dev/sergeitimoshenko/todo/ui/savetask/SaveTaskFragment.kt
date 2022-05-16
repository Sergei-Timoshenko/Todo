package dev.sergeitimoshenko.todo.ui.savetask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import dev.sergeitimoshenko.todo.R
import dev.sergeitimoshenko.todo.databinding.FragmentSaveTaskBinding
import dev.sergeitimoshenko.todo.entities.Task
import dev.sergeitimoshenko.todo.ui.TodoViewModel

@AndroidEntryPoint
class SaveTaskFragment : Fragment(R.layout.fragment_save_task) {

    private lateinit var binding: FragmentSaveTaskBinding
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSaveTaskBinding.bind(view)

        binding.fabSaveTask.setOnClickListener {
            val title = binding.etSaveTitle.text.toString()

            if (title.isNotEmpty()) {
                viewModel.insertTask(Task(title))
                Navigation.findNavController(view).popBackStack()
            } else {
                Toast.makeText(requireContext(), "Enter title", Toast.LENGTH_SHORT).show()
            }
        }
    }
}