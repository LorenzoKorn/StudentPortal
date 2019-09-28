package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PortalAdapter(private val portals: List<Portal>) :
    RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    // creates an instance for the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portal_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        // android.R.id is voor adroids views. R.id is voor zelfgemaakte id's
        private val tvName: TextView = itemView.findViewById(R.id.portal_name)
        private val tvUrl: TextView = itemView.findViewById(R.id.portal_url)

        fun bind(portal: Portal) {
            tvName.text = portal.name
            tvUrl.text = portal.url
        }
    }
}