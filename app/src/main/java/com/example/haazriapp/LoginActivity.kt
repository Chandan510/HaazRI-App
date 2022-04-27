package com.example.haazriapp

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.haazriapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressBar: ProgressDialog
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = ProgressDialog(this)
        progressBar.setTitle("Please Wait")
        progressBar.setMessage("Logging In...")
        progressBar.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()



        binding.donthaveaccount.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }

        binding.forgotPasswordBtn.setOnClickListener {
            val builder:AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view =  layoutInflater.inflate(R.layout.dialogeforgotpassword,null)
            val username = view.findViewById<EditText>(R.id.useremaildialoge)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { dialogInterface, i ->
                forgotPassword(username)
            })
            builder.setNegativeButton("close",  DialogInterface.OnClickListener { dialogInterface, i ->  })

            builder.show()

        }

        binding.loginbtn.setOnClickListener{
            validateUser()
        }

    }

    private fun forgotPassword(username: EditText?) {
        firebaseAuth.sendPasswordResetEmail(username!!.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun validateUser() {
        email = binding.edtmail.text.toString()
        password = binding.edtpassword.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.edtmail.error = "Invalid Email"
        }
        else if (TextUtils.isEmpty(password)){
            binding.edtpassword.error = "Incorrect Password"
        }
        else{
            firebaselogin()
        }
    }

    private fun firebaselogin() {
        progressBar.show()
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            progressBar.dismiss()
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Login failed due to ${e.message})", Toast.LENGTH_SHORT).show()
                progressBar.dismiss()
            }
    }

    private fun checkUser() {

        val firebaseuser = firebaseAuth.currentUser
        if (firebaseuser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}