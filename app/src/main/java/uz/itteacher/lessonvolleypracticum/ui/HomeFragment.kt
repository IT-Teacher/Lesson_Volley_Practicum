package uz.itteacher.lessonvolleypracticum.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import uz.itteacher.lessonvolleypracticum.Post
import uz.itteacher.lessonvolleypracticum.PostAdapter
import uz.itteacher.lessonvolleypracticum.databinding.FragmentHomeBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    lateinit var postList: MutableList<Post>
    var url: String = "https://jsonplaceholder.org/posts"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val requestque = Volley.newRequestQueue(requireContext())
        postList = mutableListOf()



        val recyclerView: RecyclerView = binding.recycler


        val request = JsonArrayRequest(url,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {
                    for (i in 0 until response!!.length()) {
                        val resobj = response.getJSONObject(i)

                        val content = resobj.getString("content")
                        val image = resobj.getString("image")
                        val title = resobj.getString("title")
                        val updatedAt = resobj.getString("updatedAt")

                        val post = Post(content,image,title,updatedAt)
                        postList.add(post)
                        recyclerView.adapter = PostAdapter(postList)

                    }
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("TAG", "onErrorResponse: $error")
                }

            })
        requestque.add(request)



        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}