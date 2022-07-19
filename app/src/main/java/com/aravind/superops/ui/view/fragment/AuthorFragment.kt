package com.aravind.superops.ui.view.fragment

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aravind.superops.R
import com.aravind.superops.data.model.Author
import com.aravind.superops.data.model.AuthorResponseModel
import com.aravind.superops.data.model.Message
import com.aravind.superops.databinding.FragmentAuthorBinding
import com.aravind.superops.ui.view.adapter.AuthorAdapter
import com.aravind.superops.ui.viewmodel.AuthorViewmodel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthorFragment : Fragment() {

    private var binding: FragmentAuthorBinding? = null
    private val viewmodel: AuthorViewmodel by viewModels()
    lateinit var adapter: AuthorAdapter
    private var list = ArrayList<Message>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.getAuthorDetails()
        viewmodel.authLiveData.observe(viewLifecycleOwner, AuthorObserver)


        binding?.searchLayout?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                filterName(text.toString().lowercase())
            }

        })

        binding?.searchLayout?.closeImageview?.setOnClickListener {
            binding?.searchLayout?.searchEditText?.setText("")
            hideKeyboard(requireActivity())
        }
    }

    private fun filterName(s: String) {
        searchResult(s)
        val filterList = ArrayList<Message>()
        for (name in list) {
            if (name.author.name.lowercase().contains(s)) {
                filterList.add(name)
            }
        }
        binding?.searchResultLayout?.countTextview?.text = Html.fromHtml(
            resources.getQuantityString(R.plurals.serachCount,filterList.size,filterList.size),
            Html.FROM_HTML_MODE_LEGACY
        )
        adapter.setFilter(filterList)
        adapter.notifyDataSetChanged()
    }

    private val AuthorObserver = Observer<AuthorResponseModel> { response ->
        adapter = response?.messages?.let {
            list = it as ArrayList<Message>
            AuthorAdapter(list, object : AuthorAdapter.onAuthorClickListener {
                override fun onAuthorClicked(author: Author) {
                    Log.d("TAG", author.name)
                }

            })
        }!!
        binding?.apply {
            authorRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
        binding?.authorRecyclerview?.adapter = adapter
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun searchResult(searchString: String){
        if (searchString.isNotEmpty()) {
            binding?.searchResultLayout?.searchResultLayoutParent?.visibility = View.VISIBLE
            binding?.searchLayout?.closeImageview?.visibility = View.VISIBLE
            binding?.searchLayout?.searchImageview?.visibility = View.INVISIBLE
        } else {
            binding?.searchResultLayout?.searchResultLayoutParent?.visibility = View.GONE
            binding?.searchLayout?.closeImageview?.visibility = View.INVISIBLE
            binding?.searchLayout?.searchImageview?.visibility = View.VISIBLE
        }
    }
}