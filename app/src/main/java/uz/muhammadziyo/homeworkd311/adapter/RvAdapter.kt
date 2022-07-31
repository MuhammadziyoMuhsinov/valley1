package uz.muhammadziyo.homeworkd311.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.muhammadziyo.homeworkd311.databinding.ItemRvBinding
import uz.muhammadziyo.homeworkd311.models.User

class RvAdapter(var list: List<User>, val setRv: SetRv) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(user: User) {
            itemRv.login.text = user.login
            setRv.imageJoyla(user, itemRv.avatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}

interface SetRv {
    fun imageJoyla(user: User, imageView: ImageView)
}