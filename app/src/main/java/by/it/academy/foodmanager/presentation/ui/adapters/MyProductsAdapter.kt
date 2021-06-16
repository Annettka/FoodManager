package by.it.academy.foodmanager.presentation.ui.adapters

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.databinding.ItemProductBinding
import by.it.academy.foodmanager.presentation.converters.DateStringConverter
import by.it.academy.foodmanager.presentation.mappers.ProductPresentationMapper
import by.it.academy.foodmanager.presentation.models.ProductPresent
import by.it.academy.foodmanager.presentation.ui.fragments.MyProductsFragmentDirections
import by.it.academy.foodmanager.presentation.ui.viewmodels.MyProductsViewModel

class MyProductsAdapter(val viewModel: MyProductsViewModel) :
    ListAdapter<Product, MyProductsAdapter.MyProductsViewHolder>(MyProductsDiffCallback()) {

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
        fun bind(product: Product) {
            val productPresentation = ProductPresentationMapper().map(product)
            binding.product = productPresentation

            binding.btnEditProduct.setOnClickListener {
                val action =
                    MyProductsFragmentDirections.actionMyProductsFragmentToEditProductDialogFragment(
                        productPresentation
                    )
                it.findNavController().navigate(action)
            }

            binding.btnDeleteProduct.setOnClickListener {
                viewModel.deleteProduct(context, product)

            }
        }
    }
}


private class MyProductsDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem == newItem
    }
}