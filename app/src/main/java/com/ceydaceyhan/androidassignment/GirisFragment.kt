package com.ceydaceyhan.androidassignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ceydaceyhan.androidassignment.databinding.FragmentGirisBinding
import kotlinx.android.synthetic.main.fragment_giris.*


class GirisFragment : Fragment(), View.OnClickListener {
    private lateinit var girisFragment: FragmentGirisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        girisFragment = FragmentGirisBinding.inflate(inflater)
        return girisFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPreferences()
        girisFragment.buttonGiris.setOnClickListener(this)
    }

    fun initPreferences() {
        val sp = context?.getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)

        val ogm = sp?.getString("mail", "mail yok")
        val ogs = sp?.getString("sifre", "şifre yok")

        if (ogm == "ceyda@gmail.com" && ogs == "123") {
            findNavController().navigate(R.id.anaEkranaGecis)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            girisFragment.buttonGiris.id -> {
                val sp = context?.getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)
                val m = girisFragment.editTextMail.text.toString()
                val s = girisFragment.editTextSifre.text.toString()

                if (m == "ceyda@gmail.com" && s == "123") {

                    val editor = sp?.edit()
                    editor?.putString("mail", m)
                    editor?.putString("sifre", s)
                    editor?.apply()

                    findNavController().navigate(R.id.anaEkranaGecis)
                } else {
                    Toast.makeText(context, "Hatalı Giriş!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
