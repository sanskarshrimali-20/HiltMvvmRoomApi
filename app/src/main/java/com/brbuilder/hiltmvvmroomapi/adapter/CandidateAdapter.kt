package com.brbuilder.hiltmvvmroomapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brbuilder.hiltmvvmroomapi.databinding.EachRowBinding
import com.brbuilder.hiltmvvmroomapi.models.Candidate

class CandidateAdapter(
    private var results: List<Candidate>,
    private val itemClickListener: OnItemClickListener,
    var context: Context
) : RecyclerView.Adapter<CandidateAdapter.MyViewHolder>()
{
    private lateinit var eachRowBinding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        eachRowBinding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(eachRowBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = results[position]
        holder.eachRowBinding.apply {
            txtExpenseVenueTitle.text = data.employee_name
        }
        holder.bind(data, itemClickListener)
    }

    override fun getItemCount(): Int = results.size

    class MyViewHolder(eachRowBinding: EachRowBinding) :
        RecyclerView.ViewHolder(eachRowBinding.root) {
        var eachRowBinding: EachRowBinding
        init {
            this.eachRowBinding = eachRowBinding
        }
        fun bind(
            data: Candidate,
            itemClickListener: OnItemClickListener
        ) {
            eachRowBinding.cvExpenseList.setOnClickListener {
                itemClickListener.onItemClicked(data)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(data: Candidate)
    }

    fun setData(expensesResponse: List<Candidate>) {
        this.results = expensesResponse
    }
}