package com.example.flowers_shop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers_shop.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private var binding_: ActivityCheckoutBinding? = null
    private val binding get() = binding_!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_ = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPayment.setOnClickListener {
            var fullName = binding.fullName.text
            var phone = binding.phone.text
            val email = binding.email.text
            val recName = binding.receiverName.text
            val recPhone = binding.receiverPhone.text
            val recAddress = binding.receiverAddress.text
            if (fullName.isNullOrEmpty() || phone.isNullOrEmpty() || email.isNullOrEmpty() || recName.isNullOrEmpty() || recPhone.isNullOrEmpty() || recAddress.isNullOrEmpty()) {
                Toast.makeText(this, "The field must not be empty", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Your order is ongoing", Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                finish()
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}