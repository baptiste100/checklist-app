package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R
import perso.checklistapp.databinding.FragmentListTasksBinding
import perso.checklistapp.databinding.FragmentTaskBinding
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList
import perso.checklistapp.model.relation.ListWithTasks
import perso.checklistapp.viewmodel.ListViewModel
import perso.checklistapp.viewmodel.TaskViewModel
import androidx.core.graphics.scale
import androidx.core.graphics.drawable.toDrawable

class ListTasksFragment : Fragment(R.layout.fragment_list_tasks) {

    private var _listId: Int = 0
    private lateinit var _listWithTasks: ListWithTasks
    private val taskViewModel: TaskViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()
    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var textViewListName: TextView
    private lateinit var editTextNewTask: EditText
    private lateinit var buttonEdit: ImageButton
    private lateinit var toolbar: Toolbar

    private var onEditMode: Boolean = false

    private var _binding: FragmentListTasksBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _listId = arguments?.getInt("listId") ?: return
        setRecyclerView(view)
        setViews(view)
    }

    private fun setRecyclerView(view: View) {
        taskAdapter = TaskAdapter(taskViewModel)
        val recyclerViewTasks: RecyclerView = view.findViewById(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        recyclerViewTasks.adapter = taskAdapter
    }

    @SuppressLint("ResourceType")
    private fun setViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        textViewListName = view.findViewById(R.id.textViewListName)
        editTextNewTask = view.findViewById(R.id.editTextNewTask)
        buttonEdit = view.findViewById(R.id.buttonEdit)

        setBackButton()

        listViewModel.getListWithTasks(_listId).observe(viewLifecycleOwner) { listWithTasks ->
            taskAdapter.submitList(listWithTasks.tasks)
            _listWithTasks = listWithTasks
            textViewListName.text = _listWithTasks.list.listName
        }

        view.findViewById<Button>(R.id.buttonAddTask).setOnClickListener {
            addTaskOnList()
        }
        view.findViewById<ImageButton>(R.id.buttonDelete).setOnClickListener {
            deleteList()
        }
        buttonEdit.setOnClickListener {
            changeMode()
        }
    }

    private fun setBackButton() {
        val originalBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.fleche_gauche)
        val newWidth = originalBitmap.width / 4
        val newHeight = originalBitmap.height / 4
        val scaledBitmap: Bitmap = originalBitmap.scale(newWidth, newHeight)
        val resizedDrawable = scaledBitmap.toDrawable(resources)
        toolbar.navigationIcon = resizedDrawable
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }

    private fun addTaskOnList() {
        val taskName = editTextNewTask.text.toString()
        when {
            taskName.isEmpty() -> Toast.makeText(context, "Empty task name", Toast.LENGTH_SHORT).show()
            taskName.length > 25 -> Toast.makeText(context, "Task name too long", Toast.LENGTH_SHORT).show()
            else -> {
                val task = Task(0, _listId, taskName)
                taskViewModel.insert(task)
            }
        }
        editTextNewTask.setText("")
    }

    private fun deleteList() {
        for (task: Task in _listWithTasks.tasks) {
            taskViewModel.delete(task)
        }
        listViewModel.delete(_listWithTasks.list)
        requireActivity().onBackPressedDispatcher.onBackPressed()
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