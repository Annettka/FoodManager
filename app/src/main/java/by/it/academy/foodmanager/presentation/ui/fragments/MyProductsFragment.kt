package by.it.academy.foodmanager.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.presentation.ui.adapters.MyProductsAdapter
import by.it.academy.foodmanager.presentation.ui.viewmodels.MyProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyProductsFragment : Fragment() {

    private val myProductsViewModel: MyProductsViewModel by viewModel<MyProductsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvMyProducts = view.findViewById<RecyclerView>(R.id.my_products_rv)
        val adapter = MyProductsAdapter(myProductsViewModel)
        rvMyProducts.layoutManager = LinearLayoutManager(context)
        rvMyProducts.adapter = adapter
        myProductsViewModel.allProducts.observeForever(Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        val fabAddProduct = view.findViewById<FloatingActionButton>(R.id.fab_add_product)
        fabAddProduct.setOnClickListener {
            val action =
                MyProductsFragmentDirections.actionMyProductsFragmentToSubmitProductFragment()
            view.findNavController().navigate(action)
        }
    }

}