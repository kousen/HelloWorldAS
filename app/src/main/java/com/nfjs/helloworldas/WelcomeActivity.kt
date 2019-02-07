package com.nfjs.helloworldas

import android.app.*
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*

class WelcomeActivity : Activity(), NameFragment.Rateable {

    private val ratings = HashMap<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        if (actionBar != null) {
            actionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        val name = intent.getStringExtra("name")
        val format = getString(R.string.greeting)
        greeting_text.text = String.format(format, name)
        notifyUser(name)

        DisplayNamesTask().execute(name)

    }

    private inner class DisplayNamesTask : AsyncTask<String, Void, List<String>>() {

        override fun doInBackground(vararg params: String): List<String> {
            val db = Room.databaseBuilder(applicationContext,
                    AppDatabase::class.java, "users.db").build()

            val name = params[0]

            val userDao = db.userDao
            if (!userDao.exists(name)) {
                userDao.insertUsers(User(name))
            }

            val users = userDao.allUsers
            val names = ArrayList<String>()
            for (user in users) {
                names.add(user.name)
            }

            db.close()

            return names
        }

        override fun onPostExecute(names: List<String>) {
            super.onPostExecute(names)
            val arrayAdapter = ArrayAdapter(
                    this@WelcomeActivity,
                    android.R.layout.simple_list_item_1,
                    names)

            list_view.adapter = arrayAdapter

            list_view.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.d("TAG", "Item at $position clicked")
                val name = parent.getItemAtPosition(position).toString()
                greeting_text.text = String.format(getString(R.string.greeting),
                        name)

                val fragment = NameFragment()
                val arguments = Bundle()
                arguments.putString("name", name)
                fragment.arguments = arguments
                fragment.show(fragmentManager, "Nothing")
            }
        }
    }

    private fun notifyUser(name: String) {
        val intent = Intent(this, MyActivity::class.java)
        val tsb = TaskStackBuilder.create(this)
        tsb.addParentStack(MyActivity::class.java)
        tsb.addNextIntent(intent)
        val pendingIntent = tsb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Greeted: $name")
                .setAutoCancel(true)
                .setTicker("Greeted: $name")
                .setContentIntent(pendingIntent)
                .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

    override fun modifyRating(name: String, amount: Int) {
        if (ratings[name] != null) {
            ratings[name] = ratings[name]!! + amount
        } else {
            ratings[name] = amount
        }
        Toast.makeText(this, String.format("%s has rating %d", name, ratings[name]),
                Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("display", greeting_text.text.toString())

        val names = ratings.keys.toTypedArray()
        outState.putStringArray("names", names)
        for (name in names) {
            outState.putInt(name, ratings[name] ?: 0)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        greeting_text.text = savedInstanceState.getString("display")
        val names = savedInstanceState.getStringArray("names")
        for (name in names!!) {
            ratings[name] = savedInstanceState.getInt(name)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.welcome, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    companion object {
        val NOTIFICATION_ID = 314159
    }
}
