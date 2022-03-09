package com.example.tamrinninthq2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tamrinninthq2.databinding.FragmentSaveInfoBinding
import kotlin.math.E

var sharedPreferences: SharedPreferences?=null

class SaveInfoFragment : Fragment() {
    lateinit var binding: FragmentSaveInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSaveInfoBinding.inflate(layoutInflater,container,false)
        sharedPreferences= this.activity?.getSharedPreferences("Info", Context.MODE_PRIVATE)
        getInfo()
        saveInfo()
        return binding.root
    }
    private fun getInfo(){
           binding.tvFullName.text=requireArguments().getString(FullName)
           binding.tvUsername.text=requireArguments().getString(UserName)
           binding.tvEmail.text=requireArguments().getString(Email)
           binding.tvPassword.text=requireArguments().getString(Password)
           binding.tvGenderSf.text=requireArguments().getString(Gender)
    }
    private fun saveInfo(){
       val editor= sharedPreferences?.edit()
        binding.btnSave.setOnClickListener {
            editor?.putString(FullName, binding.tvFullName.text.toString())
            editor?.putString(UserName, binding.tvUsername.text.toString())
            editor?.putString(Email, binding.tvEmail.text.toString())
            editor?.putString(Password, binding.tvPassword.text.toString())
            editor?.putString(Gender, binding.tvGenderSf.text.toString())
            editor?.apply()
            findNavController().navigate(R.id.action_saveInfoFragment_to_registerFragment)
        }
    }
}