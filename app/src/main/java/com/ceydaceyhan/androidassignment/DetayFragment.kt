package com.ceydaceyhan.androidassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceydaceyhan.androidassignment.databinding.FragmentDetayBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetayFragment : Fragment() {

    private lateinit var detayFragment: FragmentDetayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detayFragment = FragmentDetayBinding.inflate(inflater)
        return detayFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSingleUser()

    }

    fun getSingleUser() {
        val userId = arguments?.getString("selectedItem")
        userId?.toInt()?.let {
            Client.getClient().getSingleUser(it).enqueue(object : Callback<DetayResponse> {
                override fun onResponse(
                    call: Call<DetayResponse>,
                    response: Response<DetayResponse>) {

                    response.body().let {
                        detayFragment.textView.text = it?.data?.id.toString()
                        detayFragment.textView2.text = it?.data?.firstName
                        detayFragment.textView3.text = it?.data?.lastName
                        Picasso.get().load(it?.data?.avatar).into(detayFragment.circleImageView)
                    }
                }
                override fun onFailure(call: Call<DetayResponse>, t: Throwable) {
                    Log.d("TAG","ON FAILURE: ${t.localizedMessage}")

                }
            })
        }
    }

}