package com.example.weatherapp.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider
import com.example.weatherapp.MainActivity
import com.example.weatherapp.databinding.FragmentLoginBinding
import com.example.weatherapp.firebase.NotificationData
import com.example.weatherapp.firebase.PushNotification
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging

const val TOPIC = "/topics/myTopic2"

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

   private lateinit var gso: GoogleSignInOptions
  private lateinit  var gsc: GoogleSignInClient

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      //  FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        binding = FragmentLoginBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(requireActivity(), gso)

        binding.loginButton.setOnClickListener {

            signIn()
        }


        viewModel.navigateToPage.observe(viewLifecycleOwner, Observer {
            if ( false != it ) {
                this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCityFragment())
                viewModel.displayPageDetailsComplete()
            }
        })

        return binding.root
    }

   private fun signIn() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                 viewModel.displayPageDetails()

                PushNotification(
                    NotificationData("Login Details", "SUCCESSFUL"),
                    TOPIC
                ).also {
                    viewModel.sendNotification(it)
                }

            } catch (e: ApiException) {
                Toast.makeText(
                    ApplicationProvider.getApplicationContext<Context>(),
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

/*
    fun signOut() {
        gsc.signOut().addOnCompleteListener {

        }
    }
*/

}