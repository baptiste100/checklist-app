package perso.checklistapp.ui.tasks

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R
import perso.checklistapp.databinding.FragmentTaskBinding
import perso.checklistapp.viewmodel.TasksViewModel

class TasksFragment : Fragment(R.layout.fragment_task) {

    private val dataSet = mutableListOf("Téléphone", "Chargeur Tel", "Ordinateur", "Chargeur PC", "Livres", "Pain de mie", "Pomme")

    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentTaskBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val taskViewModel =
            ViewModelProvider(this).get(TasksViewModel::class.java)
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
        view.findViewById<Button>(R.id.buttonAddTask)
            .setOnClickListener {
                dataSet.add(editTextNewTask.text.toString());
                taskAdapter.notifyDataSetChanged()
            }
    }

    private fun setRecyclerView(view: View) {
        taskAdapter = TaskAdapter(dataSet)
        val recyclerViewTasks: RecyclerView = view.findViewById(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        recyclerViewTasks.adapter = taskAdapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
        recyclerViewTasks.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.bottom = spacingInPixels
            }
        })
    }
}