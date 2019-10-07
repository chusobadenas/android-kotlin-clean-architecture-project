package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.di.ApplicationContext
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import java.util.*
import javax.inject.Inject

/**
 * Adapter that manages a collection of {@link UserModel}.
 */
class UserAdapter
@Inject
constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnItemClickListener {
        fun onUserItemClicked(user: User)
    }

    private var users: List<User> = emptyList()
    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var onItemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val context = holder.itemView.context
        val user = users[position]
        UIUtils.loadImageUrl(context, holder.userImage, getImageUrl(user.fullName))
        holder.textViewTitle.text = user.fullName
        holder.itemView.setOnClickListener {
            onItemClickListener?.onUserItemClicked(user)
        }
    }

    private fun getImageUrl(name: String?): String {
        var url = "https://c1.staticflickr.com/3/2673/4099652396_1b2387aa32.jpg"

        // Ugly, but the API doesn't provide images - so this is just for example image loading
        when (name?.toLowerCase(Locale.getDefault())) {
            "simon hill" -> url =
                "https://s3.amazonaws.com/rapgenius/Homer_Simpson_Vector_by_bark2008.png"
            "peter graham" -> url =
                "https://s-media-cache-ak0.pinimg.com/736x/b9/fd/20/b9fd20744ad6f008787ffed46a0b7149.jpg"
            "angelina johnston" -> url =
                "http://web.pdx.edu/~ngorman/nafiegorman/Draw%20Simpsons%20in%205%20steps1/img/lisa0.png"
            "josh hunt" -> url =
                "http://vignette2.wikia.nocookie.net/simpsons/images/d/de/Barney_Gumble.png/revision/latest?cb=20120121184948&path-prefix=it"
            else -> {
            }
        }

        return url
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.image_user)
        lateinit var userImage: ImageView

        @BindView(R.id.text_name)
        lateinit var textViewTitle: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
