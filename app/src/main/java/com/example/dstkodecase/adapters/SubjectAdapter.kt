package com.example.dstkodecase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dstkodecase.R
import com.example.dstkodecase.adapters.SubjectAdapter.SubjectViewHolder
import com.example.dstkodecase.model.Subject
import com.example.dstkodecase.model.Table
import kotlinx.android.synthetic.main.subject_list_item.view.*

class SubjectAdapter(
    var list: List<Subject>,
    val getTableForSubject: (subjectID: String, tablesCallBack: (List<Table>) -> Unit) -> Unit
) : RecyclerView.Adapter<SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subject_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = list[position]

        holder.desc.text = subject.description
        holder.expandBtn.visibility = View.VISIBLE
        if (subject.isExpanded) {

            holder.list.layoutManager = LinearLayoutManager(holder.itemView.context)

            if (subject.hasSubjects) {
                holder.list.adapter = SubjectAdapter(
                    list[position].subjects,
                    getTableForSubject
                )
            } else {
                if (subject.tables.isNotEmpty()){
                    holder.list.adapter = TableAdapter(subject.tables)
                }else{
                    holder.expandBtn.visibility = View.GONE
                }
            }
        }
        if (subject.isExpanded) {
            holder.expandBtn.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_expand_less_black_24dp))
            holder.list.visibility = View.VISIBLE
        } else {
            holder.list.visibility = View.GONE
            holder.expandBtn.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_expand_more_black_24dp))
        }


        holder.expandBtn.setOnClickListener {
            subject.isExpanded = !subject.isExpanded
            if (subject.hasSubjects) {
                notifyItemChanged(position)

            } else {
                getTableForSubject(subject.id) {
                    subject.tables = it
                    notifyItemChanged(position)
                }
            }

        }
    }


    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val desc: TextView = itemView.subject_list_desc
        val list: RecyclerView = itemView.subject_list_nested_list
        val expandBtn = itemView.subject_list_expand
    }
}