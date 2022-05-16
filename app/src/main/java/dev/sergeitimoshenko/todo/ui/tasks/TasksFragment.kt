package dev.sergeitimoshenko.todo.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.sergeitimoshenko.todo.R
import dev.sergeitimoshenko.todo.databinding.FragmentSaveTaskBinding
import dev.sergeitimoshenko.todo.databinding.FragmentTasksBinding
import dev.sergeitimoshenko.todo.entities.Task
import dev.sergeitimoshenko.todo.ui.TodoViewModel

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks), TaskAdapter.OnItemClickListener {

    private lateinit var binding: FragmentTasksBinding
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)

        val taskAdapter = TaskAdapter(this)
        binding.rvTasks.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    val task = viewModel.tasks.value!![position]
                    viewModel.deleteTask(task)
                }
            }
        }).attachToRecyclerView(binding.rvTasks)

        binding.fabAddTask.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_tasksFragment_to_saveTaskFragment)
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }
    }

    override fun onItemClick(task: Task, view: View) {
        val action = TasksFragmentDirections.actionTasksFragmentToUpdateTaskFragment(task)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.updateTask(task.copy(completed = isChecked))
    }
}