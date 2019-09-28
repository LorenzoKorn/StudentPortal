package com.example.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORTAL_REQUEST_CODE = 100;

class MainActivity : AppCompatActivity() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        init()

        fab.setOnClickListener {
            openAddPortalScreen()
        }
    }

    private fun init() {
        // initialize recycler view with two columns in a grid
        rvPortals.layoutManager =
            GridLayoutManager(this@MainActivity, 2, RecyclerView.VERTICAL, false)
        rvPortals.adapter = portalAdapter
        rvPortals.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity, DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun openAddPortalScreen() {
        val intent = Intent(this, AddPortal::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    //get the portal object from the 'envelope'
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)                     // adds the portal to the list
                    portalAdapter.notifyDataSetChanged()    // refreshed the recyclerView
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
