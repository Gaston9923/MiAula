package com.example.miaula.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.example.miaula.MainActivity
import com.example.miaula.R
import com.example.miaula.databinding.FragmentWelcomeBinding
import com.example.miaula.ui.WelcomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class WelcomeFragment : Fragment(), NavController.OnDestinationChangedListener {

    private lateinit var binding: FragmentWelcomeBinding

    //LOGIN GOOGLE
    private var googleSignInClient: GoogleSignInClient? = null
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        createRequest()
        initUI()
        return binding.root
    }


    private fun initUI(){
        binding.btnSignGoogle.setOnClickListener { signIn() }
        binding.btnRegister.setOnClickListener { findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment) }
        binding.btnGoToHome.setOnClickListener { MainActivity.start(requireContext()) }
    }

    private fun createRequest() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(activity!!, gso)
    }

    private fun signIn() {
        val signIntent = googleSignInClient!!.signInIntent
        startActivityForResult(signIntent, RC_SIGN_IN)
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }

    private fun autenticationFirebase(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //Si inicio correctamente
                val user = firebaseAuth.currentUser
//                    if (task.getResult().getAdditionalUserInfo().isNewUser()){
                val uid = user!!.uid
                val correo = user.email
                val name = user.displayName
                val number = user.phoneNumber
                val photo = user.photoUrl
//                userLogin = User(uid, correo, name, number, photo)
                Toast.makeText(context, "Usuario:$uid$correo$name", Toast.LENGTH_LONG)
                    .show()
                //                    }
                //Ir al inicio de la app
                val mainActivityIntent = Intent(context, MainActivity::class.java)
                val bundle = Bundle()
//                bundle.putSerializable("userLogin", userLogin)
                //                    mainActivityIntent.putExtra("userLogin",userLogin);
                mainActivityIntent.putExtras(bundle)
                startActivity(mainActivityIntent)
            } else {
                Toast.makeText(
                    context,
                    "Hubo un error al iniciar sesión, intente nuevamente!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Resultado devuelto al iniciar la intencion desde GoogleSignInApi.getSignIntent
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Inicio exitoso
                val account = task.getResult(ApiException::class.java)
                autenticationFirebase(account)
            } catch (e: ApiException) {
            }
        }
    }



    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {

    }

}