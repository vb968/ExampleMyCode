package com.b2b.rqst.ui.main.pagereq

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b2b.rqst.R
import com.b2b.rqst.model.ChatTest

class PageRequestAdapter(private var chatList: List<ChatTest>, private val onClick: (ChatTest) -> Unit) : RecyclerView.Adapter<PageRequestAdapter.ViewHolder>() {
    constructor(onClick: (ChatTest) -> Unit) : this(emptyList(), onClick)

    class ViewHolder(view: View, val onClick: (ChatTest) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentRequest: ChatTest? = null
       /* val price: TextView = view.findViewById(R.id.price)
        val requestNumber: TextView = view.findViewById(R.id.request_number)
        val status: TextView = view.findViewById(R.id.status)
        val chevron: ConstraintLayout = view.findViewById(R.id.chevron)
        val chevronDown: ImageView = view.findViewById(R.id.chevron_down)
        val chevronUp: ImageView = view.findViewById(R.id.chevron_up)
        val recyclerForm: RecyclerView = view.findViewById(R.id.recycler_form)*/

        val context: Context
        init {
            context = view.context
           /* chevron.setOnClickListener {
                if (chevronDown.visibility == View.VISIBLE){
                    clickDown()
                }else{
                    clickUp()
                }
            }*/
        }
        fun bind(request: ChatTest) {
            currentRequest = request
        }
       /* private fun clickDown(){
            chevronDown.visibility = View.GONE
            chevronUp.visibility = View.VISIBLE
            recyclerForm.visibility = View.VISIBLE

        }
        private fun clickUp(){
            chevronDown.visibility = View.VISIBLE
            chevronUp.visibility = View.GONE
            recyclerForm.visibility = View.GONE
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (chatList.isEmpty()){return}

        holder.bind(chatList[position])
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
        return chatList.size
    }
    fun updateTickets(newTicketList: List<ChatTest>){
        chatList = newTicketList
        notifyDataSetChanged()
    }
    fun updateTickets(){
        notifyDataSetChanged()
    }

}