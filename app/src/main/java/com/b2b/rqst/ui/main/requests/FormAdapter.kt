package com.b2b.rqst.ui.main.requests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.b2b.rqst.R
import com.b2b.rqst.model.Form

class FormAdapter(private var formList: List<Form>) : RecyclerView.Adapter<FormAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var currentTicket: Form? = null
        val icon: ImageView = view.findViewById(R.id.image_icon)
        val label: TextView = view.findViewById(R.id.text_option)
        val value: TextView = view.findViewById(R.id.text_value)
        val context: Context
        init {
            context = view.context
           /* chevron.setOnClickListener {
                if (chevronDown.visibility == View.VISIBLE){
                    clickDown()
                }else{
                    clickUp()
                }

                currentTicket?.let {
                    onClick(it)
                }
            }*/
        }
        fun bind(ticket: Form) {
            currentTicket = ticket
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_form, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (formList.isEmpty()){return}
        if (formList[position].icon == null){
            holder.icon.visibility = View.GONE
        }
       /* holder.numberTicket.text = holder.context.getString(R.string.grill_, ticketList[position].number.toString())
        holder.titleText.text = ticketList[position].group?.number
        holder.prizeMoney.text = ticketList[position].reward.toString()
        holder.typeTicket.text = ticketList[position].template.name
        holder.priceTicket.text = ticketList[position].price.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = ZonedDateTime.parse(ticketList[position].group.date, DateTimeFormatter.ISO_DATE_TIME)
            val dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ")
            holder.dateTicket.text = date.format(dateTimeFormat)
        }else{
            val format = SimpleDateFormat(Const.SERVER_DATE_FORMAT)
            val date: Date = format.parse(ticketList[position].group.date)
            val newFormat = SimpleDateFormat("HH:mm dd-MM-yyyy")
            holder.dateTicket.text = newFormat.format(date)
        }
        holder.bind(ticketList[position])
//        holder.imageTicket.load(Const.IMAGE_TICKET)
        holder.openGame.isClickable = true
        val preference = androidx.preference.PreferenceManager.getDefaultSharedPreferences(holder.context)
        val imageString = preference.getString(Const.IMAGE_TICKET_SAVE, null)
        if (imageString != null){
            val bytes = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.size)
            holder.imageTicket.setImageBitmap(bitmap)
        }*/
    }

    override fun getItemCount(): Int {
        return formList.size
    }
    fun updateTickets(newTicketList: List<Form>){
        formList = newTicketList
        notifyDataSetChanged()
    }
    fun updateTickets(){
        notifyDataSetChanged()
    }

}