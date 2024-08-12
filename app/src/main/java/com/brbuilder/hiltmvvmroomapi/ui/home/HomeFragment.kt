package com.brbuilder.hiltmvvmroomapi.ui.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brbuilder.hiltmvvmroomapi.R
import com.brbuilder.hiltmvvmroomapi.adapter.CandidateAdapter
import com.brbuilder.hiltmvvmroomapi.base.BaseFragment
import com.brbuilder.hiltmvvmroomapi.database.CandidateViewModel
import com.brbuilder.hiltmvvmroomapi.databinding.FragmentHomeBinding
import com.brbuilder.hiltmvvmroomapi.models.Candidate
import com.brbuilder.hiltmvvmroomapi.models.CandidateResponseClass
import com.brbuilder.hiltmvvmroomapi.util.ApiState
import com.brbuilder.hiltmvvmroomapi.util.Utilities.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home), CandidateAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var postAdapter: CandidateAdapter
    private val mainViewModel: CandidateListViewModel by viewModels()
    private val alertViewModel: CandidateViewModel by viewModels()
    private var alertsList: List<Candidate> = ArrayList()

    override fun setUpLayout() {
        binding = FragmentHomeBinding.bind(requireView())
        initializeViews()
        intializeData()
    }

    private fun initializeViews() {

        postAdapter = CandidateAdapter(ArrayList(), this, requireContext())
        binding.rvExpenseList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                v: RecyclerView,
                h: RecyclerView.ViewHolder,
                t: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(h: RecyclerView.ViewHolder, dir: Int) =
                deleteItemFromDb(h.adapterPosition)
        }).attachToRecyclerView(binding.rvExpenseList)
    }

    private fun deleteItemFromDb(adapterPosition: Int) {
        alertViewModel.delete(alertsList.get(adapterPosition))
        lifecycleScope.launch {
            delay(1000L)

        }
    }

    private fun intializeData() {
        alertViewModel.getList.observe(this, Observer {response->
            alertsList = (response as ArrayList<Candidate>)
            if (alertsList.isNotEmpty()){
                //load from db
                postAdapter.setData(response)
                postAdapter.notifyDataSetChanged()
            }
            else{
                // get data from server
                callApi()
            }
        })
    }

    private fun callApi() {
        mainViewModel.getExpenseListItem()
        lifecycleScope.launchWhenStarted {
            mainViewModel.postStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
                    }
                    is ApiState.Failure -> {
                        showToast(requireContext(), it.msg.toString())
                    }
                    is ApiState.Success<*> -> {
                        binding.rvExpenseList.isVisible = true
                        val result = it.result as CandidateResponseClass
                        alertsList = result.data
                        postAdapter.setData(result.data)
                        postAdapter.notifyDataSetChanged()
                        for (i in 0 until result.data.size) {
                            //saving to db
                            alertViewModel.insert(result.data.get(i))
                        }
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }
    }


    override fun onItemClicked(data: Candidate) {

    }


}