package by.it.academy.foodmanager.app.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.foodmanager.databinding.ItemProductBinding
import by.it.academy.foodmanager.app.domain.models.ProductDomain
import by.it.academy.foodmanager.app.presentation.ui.fragments.MyProductsFragmentDirections
import by.it.academy.foodmanager.app.presentation.ui.viewmodels.MyProductsViewModel

class MyProductsAdapter(val viewModel: MyProductsViewModel) :
    ListAdapter<ProductDomain, MyProductsAdapter.MyProductsViewHolder>(MyProductsDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return MyProductsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(
        holder: MyProductsViewHolder,
        position: Int
    ) {
        val product = getItem(position)
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class MyProductsViewHolder(private val binding: ItemProductBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductDomain) {
            binding.product = product

            binding.btnEditProduct.setOnClickListener {
                val action =
                    MyProductsFragmentDirections.actionMyProductsFragmentToEditProductDialogFragment(product)
                it.findNavController().navigate(action)
            }

            binding.btnDeleteProduct.setOnClickListener {
                viewModel.deleteProduct(context, product)
            }
        }
    }
}


private class MyProductsDiffCallback : DiffUtil.ItemCallback<ProductDomain>() {

    override fun areItemsTheSame(
        oldItem: ProductDomain,
        newItem: ProductDomain
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: ProductDomain,
        newItem: ProductDomain
    ): Boolean {
        return oldItem == newItem
    }
}