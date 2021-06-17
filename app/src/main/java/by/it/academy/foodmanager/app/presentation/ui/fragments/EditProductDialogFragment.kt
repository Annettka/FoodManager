package by.it.academy.foodmanager.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.databinding.FragmentDialogEditProductBinding
import by.it.academy.foodmanager.app.domain.models.ProductDomain
import by.it.academy.foodmanager.app.domain.converters.MillisTimeConverter
import by.it.academy.foodmanager.app.presentation.ui.pickers.DatePickerFragment
import by.it.academy.foodmanager.app.presentation.ui.pickers.TimePickerFragment
import by.it.academy.foodmanager.app.presentation.ui.viewmodels.AddEditProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer

class EditProductDialogFragment : DialogFragment() {

    private val args: EditProductDialogFragmentArgs by navArgs()
    private lateinit var editProductBinding: FragmentDialogEditProductBinding
    private val addEditProductViewModel: AddEditProductViewModel by viewModel<AddEditProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProductBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dialog_edit_product,
            container,
            false
        )
        return editProductBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val converter = MillisTimeConverter()
        var timeToNotify: Long = 0

        addEditProductViewModel.apply {

            setNotificationTime(args.product.timeToNotify)
            setDaysBeforeExpiration(args.product.daysToNotify)
            creationDate.observe(viewLifecycleOwner, Observer {
                editProductBinding.etEditableCreationDate.setText(it)
            })
            expirationDate.observe(viewLifecycleOwner, Observer {
                editProductBinding.etEditableExpirationDate.setText(it)
            })
            notificationTime.observe(viewLifecycleOwner, Observer {
                editProductBinding.etEditableNotificationTime.setText(converter.millisToHMm(it))
                timeToNotify = it
            })
        }

        editProductBinding.apply {
            editableProduct = args.product
            etEditableNotificationTime.setText(
                converter.millisToHMm(args.product.timeToNotify)
            )
            etEditableDaysNumber.setText(args.product.daysToNotify.toString())

            editExpirationDate.setOnClickListener {
                showDatePickerDialog()
            }

            etEditableNotificationTime.setOnClickListener {
                showTimePickerDialog()
            }

            btnEditableCancel.setOnClickListener {
                val action =
                    EditProductDialogFragmentDirections.actionEditProductDialogFragmentToMyProductsFragment()
                view.findNavController().navigate(action)
            }

            fabSaveChanges.setOnClickListener {
                if (etEditableProductName.text.isNotEmpty() || etEditableProductComment.text.isNotEmpty()) {

                    val product =
                        ProductDomain(
                            name = etEditableProductName.text.toString(),
                            comment = etEditableProductComment.text.toString(),
                            creationDate = etEditableCreationDate.text.toString(),
                            expirationDate = etEditableExpirationDate.text.toString(),
                            subcategoryName = args.product.subcategoryName,
                            daysToNotify = if (etEditableDaysNumber.text.isNotEmpty()) {
                                etEditableDaysNumber.text.toString().toLong()
                            } else 0,
                            timeToNotify = timeToNotify
                        )


                    addEditProductViewModel.apply {
                        updateProduct(product)
                        setDaysBeforeExpiration(
                            if (etEditableDaysNumber.text.isNotEmpty()) {
                                etEditableDaysNumber.text.toString().toLong()
                            } else 0
                        )
                        val notificationText = etEditableProductName.text.toString()
                        createNotification(
                            requireContext(),
                            notificationText
                        )
                    }


                    val action =
                        EditProductDialogFragmentDirections.actionEditProductDialogFragmentToMyProductsFragment()
                    view.findNavController().navigate(action)

                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.toast_empty_product,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

    private fun showDatePickerDialog() {
        DatePickerFragment(addEditProductViewModel).show(childFragmentManager, "date picker")
    }

    private fun showTimePickerDialog() {
        TimePickerFragment(addEditProductViewModel).show(childFragmentManager, "time picker")
    }
}