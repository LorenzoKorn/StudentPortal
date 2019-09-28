package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)

        add_portal_btn.setOnClickListener {
            onAddClick()
        }
    }

    private fun onAddClick() {
        val name = portal_name_input.text.toString()
        val url = portal_url_input.text.toString()

        if (name.isNotBlank() && url.isNotBlank()) {
            val portal = Portal(name, url)          // creates a new portal object
            val intent = Intent()                   // creates an 'envelope' to store the portal
            intent.putExtra(EXTRA_PORTAL, portal)   // add portal to the 'envelope'
            setResult(Activity.RESULT_OK, intent)   // sends the 'envelope'
            finish()                                // finishes the process
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }
}
