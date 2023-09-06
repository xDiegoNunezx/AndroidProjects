package com.example.chatapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.MainActivity
import com.example.chatapp.R
import com.example.chatapp.adaptors.MessagesAdaptor
import com.example.chatapp.model.ChatMessage
import com.example.chatapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

class ChatActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val usersRef: CollectionReference = db.collection("users_collection")
    private val messagesRef: CollectionReference = db.collection("message_collection")
    private lateinit var sendButton: Button
    private lateinit var editTextMessage: EditText
    private lateinit var messageAdaptor: MessagesAdaptor
    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messages: MutableList<ChatMessage>
    private lateinit var currentUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messagesRecyclerView = findViewById(R.id.message_recycler_view)
        sendButton = findViewById(R.id.send_message_button)
        editTextMessage = findViewById(R.id.input_message)

        initRecyclerView()
        getCurrentUser()

        sendButton.setOnClickListener {
            insertMessage()
        }
    }

    override fun onStart() {
        super.onStart()
        messagesRef
            .orderBy("timeStamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, error ->
            error?.let {
                return@addSnapshotListener
            }
            snapshots?.let {
                for (dc in it.documentChanges) {
                    val oldIndex = dc.oldIndex
                    val newIndex = dc.newIndex

                    when(dc.type){
                        DocumentChange.Type.ADDED -> {
                            val snapshot = dc.document
                            val message = snapshot.toObject(ChatMessage::class.java)
                            messages.add(newIndex,message)
                            messageAdaptor.notifyItemInserted(newIndex)
                            messagesRecyclerView.smoothScrollToPosition(messages.size-1)
                        }
                        DocumentChange.Type.REMOVED -> {

                        }
                        DocumentChange.Type.MODIFIED -> {

                        }
                        else -> {}
                    }

                }
            }
        }
    }

    private fun initRecyclerView() {
        messages = mutableListOf()
        messageAdaptor = MessagesAdaptor(this, messages)
        messagesRecyclerView.adapter = messageAdaptor
        messagesRecyclerView.layoutManager = LinearLayoutManager(this)
        messagesRecyclerView.setHasFixedSize(true)
    }

    private fun getCurrentUser(){
        usersRef.whereEqualTo("id", FirebaseAuth.getInstance().currentUser?.uid)
            .get()
            .addOnSuccessListener {
                for(snapshot in it) {
                    currentUser = snapshot.toObject(User::class.java)
                }
            }
    }

    private fun insertMessage(){
        var message = editTextMessage.text.toString()
        if(message.isNotEmpty()){
            messagesRef.document()
                .set(ChatMessage(currentUser,message,null))
                .addOnCompleteListener {
                    if(it.isComplete) {
                        editTextMessage.setText("")
                        message = ""
                    } else {

                    }
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                Intent(this@ChatActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
                return true
            }
        }
        return false
    }

}