package by.it.academy.foodmanager.app.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import by.it.academy.foodmanager.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.submit_product).setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToSubmitProductFragment()
            view.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.my_products).setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToMyProductsFragment()
            view.findNavController().navigate(action)
        }

    }
}