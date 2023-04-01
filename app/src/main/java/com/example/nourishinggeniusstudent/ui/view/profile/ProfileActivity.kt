package com.example.nourishinggeniusstudent.ui.view.profile

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityProfileBinding
import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.ui.dialogs.MessageDialog
import com.example.nourishinggeniusstudent.ui.view.auth.AuthViewModel
import com.example.nourishinggeniusstudent.ui.view.auth.LoginActivity
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.DateUtils
import com.example.nourishinggeniusstudent.utils.showToast
import gun0912.tedimagepicker.builder.TedImagePicker
import java.util.*

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel by lazy { AuthViewModel(this) }
    private var profilePic: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        setObservers()
        initView()
    }

    private fun setObservers() {
        viewModel.deleteAccResponse.observe(this) {
            viewModel.isLoading.value = false
            showToast(it.message)
            val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        viewModel.userData.observe(this) {
            session?.user = it
            setUserData(it)
            viewModel.isLoading.value = false
        }
    }

    private fun setUserData(user: User?) {
        binding.nameInput.setText(user?.name)
        binding.emailInput.setText(user?.email)
        binding.mobileInput.setText(user?.contactNumber)
        Glide.with(this).load(
            if (profilePic != null) {
                profilePic
            } else if (user?.profilePic.isNullOrBlank()) {
                user?.profilePic
            } else R.drawable.img_student
        ).centerCrop().into(binding.imageUploader)
    }

    private fun initView() {
        viewModel.isLoading.value = true
        viewModel.userData.value = session?.user
        setUserData(session?.user)
        session?.user?.userId?.let {
            viewModel.getuserinfobyid(it)
        }
    }

    private fun setClickListeners() {
        binding.crossIcon.setOnClickListener { finish() }
        binding.btnDel.setOnClickListener {
            MessageDialog.getInstance(this@ProfileActivity, "Are you sure?").setCancellable(true)
                .setListener(object : MessageDialog.OkButtonListener {
                    override fun onOkPressed(dialog: MessageDialog) {
                        dialog.dismiss()
                        viewModel.isLoading.value = true
                        viewModel.deleteAccount(session?.user?.userId.toString())
                    }
                }).setPositiveButtonText("Delete").setNegativeButton(true, "Cancel").show()
        }
        binding.imageUploader.setOnClickListener {

            TedImagePicker.with(this).start {
                profilePic = it
                Glide.with(this).load(it).centerCrop().into(binding.imageUploader)
            }

        }

        binding.ageInput.setOnClickListener {
            openDatePicker()
        }

        binding.btnSave.setOnClickListener {
            validate()
        }
    }

    private fun openDatePicker() {
        val cal = Calendar.getInstance()
        if (!session?.user?.dob.isNullOrBlank()) {
            cal.time = DateUtils.getDate(session?.user?.dob.toString())
        }
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, { view, year, monthOfYear, dayOfMonth ->
                binding.ageInput.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun validate() {
        if (binding.nameInput.text.isNullOrBlank()) {
            binding.nameInput.error = "Name can't be blank"
            return
        }
        if (binding.mobileInput.text.isNullOrBlank()) {
            binding.mobileInput.error = "Contact Number can't be blank"
            return
        }
        if (binding.ageInput.text.isNullOrBlank()) {
            binding.ageInput.error = "Please select your Date of birth"
            return
        }
        if (binding.locationInput.text.isNullOrBlank()) {
            binding.locationInput.error = "Address can't be blank"
            return
        }
        viewModel.isLoading.value = true
        session?.user?.let {
            viewModel.editProfile(
                it.userId,
                binding.nameInput.text.toString(),
                binding.mobileInput.text.toString(),
                binding.locationInput.text.toString(),
                binding.ageInput.text.toString()
            )
        }
    }
}