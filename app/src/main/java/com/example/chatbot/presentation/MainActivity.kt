package com.example.chatbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.chatbot.R
import com.example.chatbot.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),IViewsHandling {

    private lateinit var binding : ActivityMainBinding
    private val authenticationViewModel : AuthenticationViewModel by viewModels()

    val otpValue = MutableStateFlow<String>("");

    lateinit var phoneNumber : String
    lateinit var userName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(checkAuthentication()) {

        } else {
            binding.userAuthenticationLayout.visibility = View.VISIBLE
            binding.appLogo.visibility = View.VISIBLE
            binding.userNumberLayout.visibility = View.VISIBLE
            binding.textInputLayout1.visibility = View.VISIBLE
            binding.etNumber.visibility = View.VISIBLE
            binding.btProceed.visibility = View.VISIBLE
        }
        binding.btProceed.setOnClickListener {
            if (binding.etNumber.isVisible) {
                phoneNumber = binding.etNumber.text.toString()
                authenticationViewModel.signInWithPhoneNumber("+62 $phoneNumber",this)
            } else {
                userName = binding.etName.text.toString()
                authenticationViewModel.createUserProfile(userName, userNumber = phoneNumber)
            }
        }
    }

    override fun changeViewsVisibility() {
        binding.userNumberLayout.visibility = View.GONE
        binding.textInputLayout1.visibility = View.GONE
        binding.etNumber.visibility = View.GONE
        binding.userNameLayout.visibility = View.GONE
        binding.textInputLayout2.visibility = View.GONE
        binding.etName.visibility = View.GONE
    }

    override fun getUserId(): String {
        return authenticationViewModel.getUserId()
    }

    private fun checkAuthentication(): Boolean {
        return false
    }

    fun showBottomSheet() {
        val otpFragment = OTPFragment()
        supportFragmentManager.beginTransaction().add(otpFragment,"bottomSheetOtpFragment").commit()
    }

}

