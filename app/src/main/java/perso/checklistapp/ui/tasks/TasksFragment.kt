package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
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
    private lateinit var editTextNewTask: EditText
    private lateinit var buttonEdit: ImageButton

    private var onEditMode: Boolean = false

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
        setViews(view)
    }

    private fun setRecyclerView(view: View) {
        taskAdapter = TaskAdapter(viewModel)
        val recyclerViewTasks: RecyclerView = view.findViewById(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        recyclerViewTasks.adapter = taskAdapter
    }

    private fun setViews(view: View) {
        editTextNewTask = view.findViewById(R.id.editTextNewTask);
        buttonEdit = view.findViewById(R.id.buttonEdit)

        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.submitList(tasks)
        }

        view.findViewById<Button>(R.id.buttonAddTask).setOnClickListener {
            addTask()
        }

        buttonEdit.setOnClickListener {
            changeMode()
        }
    }

    private fun addTask() {
        val taskName = editTextNewTask.text.toString()
        when {
            taskName.isEmpty() -> Toast.makeText(context, "Empty task name", Toast.LENGTH_SHORT).show()
            taskName.length > 25 -> Toast.makeText(context, "Task name too long", Toast.LENGTH_SHORT).show()
            else -> {
                val task = Task(0, null, taskName)
                viewModel.insert(task)
            }
        }
        editTextNewTask.setText("")
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun changeMode() {
        onEditMode = !onEditMode
        when (onEditMode) {
            false -> buttonEdit.setImageResource(R.drawable.edit_icon)
            true -> buttonEdit.setImageResource(R.drawable.croix_fond_violet)
        }
        taskAdapter.setEditMode(onEditMode)
        taskAdapter.notifyDataSetChanged()
    }
}