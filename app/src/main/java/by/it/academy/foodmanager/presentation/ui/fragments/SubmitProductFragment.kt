package by.it.academy.foodmanager.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.presentation.ui.adapters.SubmitProductAdapter
import by.it.academy.foodmanager.presentation.ui.viewmodels.SubmitProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubmitProductFragment : Fragment() {

    private val submitProductViewModel: SubmitProductViewModel by viewModel<SubmitProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_submit_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSubmitProduct = view.findViewById<RecyclerView>(R.id.product_categories_rv)
        val adapter = SubmitProductAdapter()
        rvSubmitProduct.layoutManager = LinearLayoutManager(context)
        rvSubmitProduct.adapter = adapter
        submitProductViewModel.productCategories.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

}