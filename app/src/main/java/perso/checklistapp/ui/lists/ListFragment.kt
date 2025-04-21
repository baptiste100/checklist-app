package perso.checklistapp.ui.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R
import perso.checklistapp.databinding.FragmentListBinding
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList
import perso.checklistapp.ui.tasks.TaskAdapter
import perso.checklistapp.ui.tasks.TaskListAdapter
import perso.checklistapp.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var recyclerViewLists: RecyclerView
    private lateinit var listAdapter: TaskListAdapter
    private lateinit var editTextNewList: EditText

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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
        listAdapter = TaskListAdapter(viewModel)
        val recyclerViewLists: RecyclerView = view.findViewById(R.id.recyclerViewLists)
        recyclerViewLists.layoutManager = LinearLayoutManager(context)
        recyclerViewLists.adapter = listAdapter
    }

    private fun setViews(view: View) {
        editTextNewList = view.findViewById(R.id.editTextNewList);

        viewModel.allLists.observe(viewLifecycleOwner) { lists ->
            listAdapter.submitList(lists)
        }

        view.findViewById<Button>(R.id.buttonAddList).setOnClickListener {
            addList()
        }
    }

    private fun addList() {
        val listName = editTextNewList.text.toString()
        when {
            listName.isEmpty() -> Toast.makeText(context, "Empty list name", Toast.LENGTH_SHORT).show()
            listName.length > 25 -> Toast.makeText(context, "List name too long", Toast.LENGTH_SHORT).show()
            else -> {
                val list = TaskList(0, listName)
                viewModel.insert(list)
            }
        }
        editTextNewList.setText("")
    }
}