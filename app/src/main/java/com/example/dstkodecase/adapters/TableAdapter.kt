package com.example.dstkodecase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dstkodecase.R
import com.example.dstkodecase.model.Table
import kotlinx.android.synthetic.main.table_list_item.view.*

class TableAdapter(var list: List<Table>) : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        return TableViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.table_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table = list[position]

        holder.text.text = table.text
    }

    class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.table_list_text
    }
}