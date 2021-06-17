package by.it.academy.foodmanager.app.presentation.ui.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.app.domain.models.ProductCategoryDomain
import by.it.academy.foodmanager.app.presentation.ui.fragments.SubmitProductFragmentDirections

class SubmitProductAdapter() :
    ListAdapter<ProductCategoryDomain, SubmitProductAdapter.SubmitProductViewHolder>(
        ProductDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubmitProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return SubmitProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubmitProductViewHolder, position: Int) {
        val categoryItem = getItem(position)
        holder.bind(categoryItem)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class SubmitProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val context: Context = itemView.context
        private val tvCategoryName: TextView = itemView.findViewById(R.id.category_name)
        private val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)
        private val linLayoutSubcategoryItems: LinearLayout =
            itemView.findViewById(R.id.subcategory_items)

        init {
            linLayoutSubcategoryItems.visibility = GONE
            var maxNumOfSubcategories: Int = 0

            for (index in 0 until currentList.size) {
                val maxSizeTemp: Int = currentList[index].subcategories.size
                if (maxSizeTemp > maxNumOfSubcategories) {
                    maxNumOfSubcategories = maxSizeTemp
                }
            }

            for (ind in 0 until maxNumOfSubcategories) {
                val textView = TextView(context)

                textView.apply {
                    id = ind
                    setPadding(4, 10, 4, 10)
                    gravity = Gravity.CENTER
                    textSize = 18F
                    background = getDrawable(context, R.drawable.subcategory_item_background)
                }

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    190
                )

                textView.setOnClickListener(this)
                linLayoutSubcategoryItems.addView(textView, layoutParams)
            }
            tvCategoryName.setOnClickListener(this)
        }

        fun bind(categoryItem: ProductCategoryDomain) {
            tvCategoryName.text = categoryItem.categoryName
            categoryIcon.setImageResource(categoryItem.categoryIcon)

            val numOfSubcategoryTextViews = linLayoutSubcategoryItems.childCount
            for (index in 0 until numOfSubcategoryTextViews) {
                val currCardView = linLayoutSubcategoryItems.getChildAt(index) as TextView
                currCardView.visibility = VISIBLE
            }

            val numOfSubcategories = categoryItem.subcategories.size
            if (numOfSubcategories < numOfSubcategoryTextViews) {
                for (index in numOfSubcategories until numOfSubcategoryTextViews) {
                    val currCardView =
                        linLayoutSubcategoryItems.getChildAt(index) as TextView
                    currCardView.visibility = GONE
                }
            }
            for (tvIndex in 0 until numOfSubcategories) {
                val curTV = linLayoutSubcategoryItems.getChildAt(tvIndex) as TextView?
                curTV?.text = categoryItem.subcategories[tvIndex].subcategoryName
            }
        }

        override fun onClick(v: View) {
            if (v.id == R.id.category_name) {
                if (linLayoutSubcategoryItems.visibility == VISIBLE) {
                    linLayoutSubcategoryItems.visibility = GONE
                } else {
                    linLayoutSubcategoryItems.visibility = VISIBLE
                }
            } else {
                val tvClicked: TextView = v as TextView
                val productCategory = tvCategoryName.text.toString()
                val productSubcategory = tvClicked.text.toString()
                val action =
                    SubmitProductFragmentDirections.actionSubmitProductFragmentToAddProductFragment(
                        productCategory,
                        productSubcategory
                    )
                v.findNavController().navigate(action)
            }
        }
    }
}

private class ProductDiffCallback : DiffUtil.ItemCallback<ProductCategoryDomain>() {

    override fun areItemsTheSame(
        oldItem: ProductCategoryDomain,
        newItem: ProductCategoryDomain
    ): Boolean {
        return oldItem.categoryName == newItem.categoryName
    }

    override fun areContentsTheSame(
        oldItem: ProductCategoryDomain,
        newItem: ProductCategoryDomain
    ): Boolean {
        return oldItem == newItem
    }
}
