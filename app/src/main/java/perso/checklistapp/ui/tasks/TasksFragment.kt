package perso.checklistapp.ui.tasks

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R
import perso.checklistapp.databinding.FragmentTaskBinding
import perso.checklistapp.db.AppDatabase
import perso.checklistapp.model.Task
import perso.checklistapp.viewmodel.TaskViewModel

class TasksFragment : Fragment(R.layout.fragment_task) {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentTaskBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(view)
        val editTextNewTask = view.findViewById<EditText>(R.id.editTextNewTask);

        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.submitList(tasks)
        }

        view.findViewById<Button>(R.id.buttonAddTask)
            .setOnClickListener {
                val task = Task(0, editTextNewTask.text.toString())
                viewModel.insert(task)
            }
    }

    private fun setRecyclerView(view: View) {
        taskAdapter = TaskAdapter()
        val recyclerViewTasks: RecyclerView = view.findViewById(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        recyclerViewTasks.adapter = taskAdapter
    }
}