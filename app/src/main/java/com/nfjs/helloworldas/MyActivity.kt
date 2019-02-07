package com.nfjs.helloworldas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_my.*

class MyActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        Log.d(TAG, "onCreate")
        val helloButton = findViewById<View>(R.id.hello_button) as Button
        helloButton.setOnClickListener { v -> sayHello(v) }
    }

    fun sayHello(view: View) {
        val name = edit_text.text.toString()
        text_view.setText(String.format("Hello, %1\$s!", name))

        val intent = Intent(this, WelcomeActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when(item.itemId) {
                R.id.action_settings -> true
                else ->
                    super.onOptionsItemSelected(item)
            }

    companion object {
        private const val TAG = "MyActivity"
    }

}
