package com.example.nourishinggeniusstudent.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.DialogMessageBinding

class MessageDialog(
    context: Context,
    private var message: String?,
) : Dialog(context, R.style.ThemeDialog) {

    private lateinit var binding: DialogMessageBinding
    private var listener: OkButtonListener? = null
    private var positiveTxt = context.getString(R.string.ok)
    private var negativeTxt = context.getString(R.string.cancel)
    private var negativeBtnVisibility = View.GONE
    private var cancellable = false

    companion object {
        fun getInstance(
            context: Context,
            message: String? = null,
        ): MessageDialog {
            return MessageDialog(context, message)
        }
    }

    fun setMessage(msg: String): MessageDialog {
        message = msg
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(cancellable)
        setCancelable(cancellable)

        binding = DialogMessageBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        if (!message.isNullOrBlank()) binding.tvMessage.text = message
        if (!positiveTxt.isNullOrBlank()) binding.btnOk.text = positiveTxt
        if (!negativeTxt.isNullOrBlank()) binding.btnCancel.text = negativeTxt
        binding.btnCancel.visibility = negativeBtnVisibility

        binding.btnOk.setOnClickListener {
            if (listener == null) dismiss()
            else listener?.onOkPressed(this)
        }

        binding.btnCancel.setOnClickListener {
            if (listener == null) dismiss()
            else listener?.onCancelClicked(this)
        }
    }

    fun setListener(listener: OkButtonListener?): MessageDialog {
        this.listener = listener
        return this
    }

    fun setNegativeButton(isVisible: Boolean, text: String = "Cancel"): MessageDialog {
        this.negativeBtnVisibility = if (isVisible) View.VISIBLE else View.GONE
        this.negativeTxt = text
        return this
    }

    fun setPositiveButtonText(text: String = "Ok"): MessageDialog {
        this.positiveTxt = text
        return this
    }

    fun setCancellable(cancellable: Boolean): MessageDialog {
        this.cancellable = cancellable
        return this
    }

    interface OkButtonListener {
        fun onOkPressed(dialog: MessageDialog)

        fun onCancelClicked(dialog: MessageDialog) {
            dialog.dismiss()
        }
    }
}
