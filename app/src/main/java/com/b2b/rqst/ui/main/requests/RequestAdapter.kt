package com.b2b.rqst.ui.main.requests

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.b2b.rqst.R
import com.b2b.rqst.model.Request
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RequestAdapter(private var ticketList: List<Request>, private val onClick: (Request) -> Unit) : RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    constructor(onClick: (Request) -> Unit) : this(emptyList(), onClick)

    class ViewHolder(view: View, val onClick: (Request) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentTicket: Request? = null
        val price: TextView = view.findViewById(R.id.price)
        val requestNumber: TextView = view.findViewById(R.id.request_number)
        val status: TextView = view.findViewById(R.id.status)
        val chevronDown: ImageView = view.findViewById(R.id.chevron_down)
        val chevronUp: ImageView = view.findViewById(R.id.chevron_up)
        val row_form_0: ConstraintLayout = view.findViewById(R.id.row_form_0)
        val row_form_1: ConstraintLayout = view.findViewById(R.id.row_form_1)
        val row_form_2: ConstraintLayout = view.findViewById(R.id.row_form_2)
        val row_form_3: ConstraintLayout = view.findViewById(R.id.row_form_3)
        val context: Context
        init {
            context = view.context
           /* openGame.setOnClickListener {
                openGame.isClickable = false

                currentTicket?.let {
                    onClick(it)
                }
            }*/
        }
        fun bind(ticket: Request) {
            currentTicket = ticket
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (ticketList.isEmpty()){return}
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
        return ticketList.size
    }
    fun updateTickets(newTicketList: List<Request>){
        ticketList = newTicketList
        notifyDataSetChanged()
    }
    fun updateTickets(){
        notifyDataSetChanged()
    }

}