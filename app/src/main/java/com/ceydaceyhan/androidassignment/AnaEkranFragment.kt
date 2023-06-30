package com.ceydaceyhan.androidassignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceydaceyhan.androidassignment.databinding.FragmentAnaEkranBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnaEkranFragment : Fragment(), View.OnClickListener {
    private lateinit var anaEkranBinding: FragmentAnaEkranBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        anaEkranBinding = FragmentAnaEkranBinding.inflate(inflater)
        return anaEkranBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anaEkranBinding.buttonCikisYap.setOnClickListener(this)
        initRecyclerView()
        getAllUsers()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            anaEkranBinding.buttonCikisYap.id -> {
                val sp = context?.getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)
                val editor = sp?.edit()
                editor?.remove("mail")
                editor?.remove("sifre")
                editor?.apply()

                findNavController().navigate(R.id.girisEkraninaGecis)
            }
        }
    }
    fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        anaEkranBinding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        anaEkranBinding.recyclerView.adapter = recyclerViewAdapter

    }

    fun getAllUsers() {
        Client.getClient().getAllUsers().enqueue(object : Callback<Users> {

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.body() != null) {

                    val usersList = response.body()?.data
                    if (usersList != null) {
                        recyclerViewAdapter.setData(usersList)
                    }
                }
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("TAG","ON FAILURE: ${t.localizedMessage}")
            }
        })
    }
}