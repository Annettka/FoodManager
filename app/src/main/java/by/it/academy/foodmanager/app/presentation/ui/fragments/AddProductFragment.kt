package by.it.academy.foodmanager.app.presentation.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.databinding.FragmentAddProductBinding
import by.it.academy.foodmanager.app.domain.converters.MillisTimeConverter
import by.it.academy.foodmanager.app.domain.models.ProductDomain
import by.it.academy.foodmanager.app.presentation.ui.pickers.DatePickerFragment
import by.it.academy.foodmanager.app.presentation.ui.pickers.TimePickerFragment
import by.it.academy.foodmanager.app.presentation.ui.viewmodels.AddEditProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : Fragment() {

    private val args: AddProductFragmentArgs by navArgs()
    private val addEditProductViewModel: AddEditProductViewModel by viewModel<AddEditProductViewModel>()
    lateinit var binding: FragmentAddProductBinding
    private val REQUEST_TAKE_PHOTO = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var timeToNotify: Long = 0

        addEditProductViewModel.apply {
            creationDate.observe(viewLifecycleOwner, Observer {
                binding.etCreationDate.setText(it)
            })
            expirationDate.observe(viewLifecycleOwner, Observer {
                binding.etExpirationDate.setText(it)
            })
            notificationTime.observe(viewLifecycleOwner, Observer {
                binding.etNotificationTime.setText(MillisTimeConverter().millisToHMm(it))
                timeToNotify = it
            })
        }

        binding.apply {
            editExpirationDate.setOnClickListener {
                showDatePickerDialog()
            }

            makePhoto.setOnClickListener {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            swithEnableNotification.setOnClickListener {
                if (swithEnableNotification.isChecked) {
                    notificationSettingsField.visibility = VISIBLE
                } else {
                    notificationSettingsField.visibility = GONE
                }
            }

            etNotificationTime.setOnClickListener {
                showTimePickerDialog()
            }

            fabSave.setOnClickListener {
                if (etProductName.text.isNotEmpty() || etProductComment.text.isNotEmpty()) {

                    val product = ProductDomain(
                        name = etProductName.text.toString(),
                        comment = etProductComment.text.toString(),
                        creationDate = etCreationDate.text.toString(),
                        expirationDate = etExpirationDate.text.toString(),
                        subcategoryName = args.productSubcategory,
                        daysToNotify = if (etDaysNumber.text.isNotEmpty()) {
                            etDaysNumber.text.toString().toLong()
                        } else 0,
                        timeToNotify = timeToNotify
                    )
                    if (!addEditProductViewModel.isProductExist(product)) {
                        addEditProductViewModel.apply {
                            insertProduct(product)
                            setDaysBeforeExpiration(
                                if (etDaysNumber.text.isNotEmpty()) {
                                    etDaysNumber.text.toString().toLong()
                                } else 0,
                            )
                            val notificationText = etProductName.text.toString()
                            createNotification(
                                requireContext(),
                                notificationText
                            )

                        }
                        val action =
                            AddProductFragmentDirections.actionAddProductFragmentToMyProductsFragment()
                        view.findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            R.string.toast_empty_product,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.toast_empty_product,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnCancel.setOnClickListener {
                val action =
                    AddProductFragmentDirections.actionAddProductFragmentToSubmitProductFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    private fun showDatePickerDialog() {
        DatePickerFragment(addEditProductViewModel).show(childFragmentManager, "date picker")
    }

    private fun showTimePickerDialog() {
        TimePickerFragment(addEditProductViewModel).show(childFragmentManager, "time picker")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val thumbnailBitmap = data?.extras?.get("data") as Bitmap
            binding.makePhoto.setImageBitmap(thumbnailBitmap)
            binding.makePhoto.maxWidth = 50
            binding.makePhoto.maxHeight = 70
            addEditProductViewModel.saveBitmap(thumbnailBitmap)

        }
    }


}