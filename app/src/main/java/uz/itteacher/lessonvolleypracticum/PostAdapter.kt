package uz.itteacher.lessonvolleypracticum

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(var postList: MutableList<Post>): RecyclerView.Adapter<PostAdapter.CustomAdapter>() {

    class CustomAdapter(item:View):RecyclerView.ViewHolder(item){
        val imageView: ImageView = itemView.findViewById(R.id.img)
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
        val date: TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return CustomAdapter(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter, position: Int) {
        val itemsViewModel = postList[position]
        holder.imageView.setImageURI(Uri.parse(itemsViewModel.image))
        holder.title.text = itemsViewModel.title
        holder.content.text = itemsViewModel.content
        holder.date.text = itemsViewModel.updatedAt


    }
}