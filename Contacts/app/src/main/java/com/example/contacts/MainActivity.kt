package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.contacts.data.DatabaseHandler
import com.example.contacts.model.Contact

class MainActivity : AppCompatActivity() {
    private lateinit var textViewData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewData = findViewById(R.id.text_view_data)

        val db = DatabaseHandler(this)

        db.addContact(Contact("Alex","1234"))
        db.addContact(Contact("John","5678"))
        db.addContact(Contact("Maria","1234"))
        db.addContact(Contact("Ana","1234"))

        val contact1 = db.getContact(1)
        val contact2 = db.getContact(2)
        db.deleteContact(contact1)
        db.deleteContact(contact2)

        val contact = db.getContact(3)
        textViewData.text = "Name: ${contact.name} \nPhone Number: ${contact.phoneNumber}"

        val newContact = contact
        newContact.name = "Alina"
        newContact.phoneNumber = "21212121"
        db.uodateContact(newContact)

        val updatedContact = db.getContact(3)
        textViewData.text = "Updated Name: ${updatedContact.name} \n Updated Phone Number: ${updatedContact.phoneNumber}"

/*        val contactList = db.getAllContacts()
        var data = ""
        for (contact in contactList) {
            data += "\nName: ${contact.name} +" +
                    "\nPhoneNumber: ${contact.phoneNumber}"
        }
        textViewData.text = data*/
        textViewData.append("The number of contacts: ${db.getContactsCount()}")


    }
}