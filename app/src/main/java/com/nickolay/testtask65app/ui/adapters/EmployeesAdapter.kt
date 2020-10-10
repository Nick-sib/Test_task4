package com.nickolay.testtask65app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.getAge
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_employees.view.*

class EmployeesAdapter (val onItemClick: ((EmployeesModel) -> Unit)? = null) :
    RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    var data: List<EmployeesModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_employees, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(employe: EmployeesModel) {
            containerView.tvName.text = "${employe.f_name} ${employe.l_name}"
            containerView.tvAge.text = employe.birthday.getAge()

            itemView.setOnClickListener {
                onItemClick?.invoke(employe)
            }
        }
    }

}