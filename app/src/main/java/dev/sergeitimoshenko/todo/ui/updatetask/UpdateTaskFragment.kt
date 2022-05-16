package dev.sergeitimoshenko.todo.ui.updatetask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.sergeitimoshenko.todo.R
import dev.sergeitimoshenko.todo.databinding.FragmentSaveTaskBinding
import dev.sergeitimoshenko.todo.databinding.FragmentUpdateTaskBinding
import dev.sergeitimoshenko.todo.entities.Task
import dev.sergeitimoshenko.todo.ui.TodoViewModel

@AndroidEntryPoint
class UpdateTaskFragment : Fragment(R.layout.fragment_update_task) {

    private lateinit var binding: FragmentUpdateTaskBinding
    private val viewModel: TodoViewModel by activityViewModels()
    private val args: UpdateTaskFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateTaskBinding.bind(view)

        binding.etUpdateTitle.setText(args.task.title)

        binding.fabUpdateTask.setOnClickListener {
            val title = binding.etUpdateTitle.text.toString()

            if (title.isNotEmpty()) {
                viewModel.updateTask(args.task.copy(title = title))
                Navigation.findNavController(view).popBackStack()
            } else {
                Toast.makeText(requireContext(), "Enter title", Toast.LENGTH_SHORT).show()
            }
        }
    }
}