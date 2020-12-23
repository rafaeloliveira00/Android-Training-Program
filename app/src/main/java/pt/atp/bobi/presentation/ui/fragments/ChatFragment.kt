package pt.atp.bobi.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.atp.bobi.data.model.Message
import pt.atp.bobi.databinding.FragmentChatBinding
import pt.atp.bobi.deviceName
import pt.atp.bobi.presentation.ui.adapters.ChatAdapter


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        setup()
        loadMessages()
    }

    private fun setup() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true

        binding.rvMessages.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = ChatAdapter()
        }

        binding.btnSend.setOnClickListener {
            val message = binding.etContent.text.toString()
            sendMessage(message)

            binding.etContent.text.clear()
        }
    }

    private fun sendMessage(content: String) {
        val message = hashMapOf(
            "user" to deviceName(),
            "content" to content,
            "timestamp" to "${System.currentTimeMillis()}"
        )

        val db = Firebase.firestore
        val id: String = db.collection("collection_name").document().id
        db.collection("atp").document(id)
            .set(message)
            .addOnSuccessListener { Log.d(TAG, "message sent") }
            .addOnFailureListener { Log.d(TAG, "failure") }
    }

    private fun loadMessages() {
        val docs = Firebase.firestore.collection("atp").orderBy("timestamp")
        docs.addSnapshotListener { snapshot, e ->
            val messages = mutableListOf<Message>()
            for (document in snapshot!!.documents) {
                val message = Message(
                    document.id,
                    "${document.data?.get("user")}",
                    "${document.data?.get("content")}",
                    "${document.data?.get("timestamp")}",
                    document.data?.get("user") == deviceName()
                )
                messages += message
            }

            val adapter = binding.rvMessages.adapter as ChatAdapter
            adapter.submitList(messages)
        }
    }
}