package com.example.tamrinninthq2

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.tamrinninthq2.databinding.FragmentRegisterBinding

const val fullName="fullName"
const val userName="userName"
const val email="email"
const val password="password"
const val Gender="gender"


class RegisterFragment : Fragment() {
    lateinit var binding:FragmentRegisterBinding
     var listOfInfo= mutableListOf<EditText>()
    var gender=""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          binding= FragmentRegisterBinding.inflate(layoutInflater,container,false)
        initView()
        return binding.root
    }

    private fun initView() {
        listOfInfo.add(binding.evFullName)
        listOfInfo.add(binding.evUsername)
        listOfInfo.add(binding.evEmail)
        listOfInfo.add(binding.evPassword)
        listOfInfo.add(binding.evReTypePassword)
        register()
    }

    private fun register() {
        binding.btnRegister.setOnClickListener {
                if(isCheck()){
                       val bundle= bundleOf( fullName to binding.evFullName.text.toString(),
                           userName to binding.evUsername.text.toString(),
                           email to binding.evEmail.text.toString(),
                           password to binding.evPassword.text.toString(),
                           Gender to gender)
                    findNavController().navigate(R.id.action_registerFragment_to_saveInfoFragment, bundle)
                }
        }
    }

    private fun isCheck():Boolean{
      var result=true
      when {
          binding.radioBtnFemale.isChecked -> gender="Female"
          binding.radioBtnMale.isChecked -> gender="Male"
          else -> {
              Toast.makeText(context, "Enter Gender!", Toast.LENGTH_SHORT).show()
              result=false
          }
      }
      listOfInfo.forEach{
          if(it.text.isBlank()) {
              it.error ="Can not be empty!"
              result= false
          }
      }

      if(binding.evPassword.toString()!=binding.evReTypePassword.toString()){
          binding.evReTypePassword.error="Password is not match!"
          result=false
      }
      return result
  }
}