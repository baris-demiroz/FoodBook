package com.example.besinkitabi.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.ViewModelProvider
import com.example.besinkitabi.databinding.FragmentBesinDetayBinding
import com.example.besinkitabi.util.getDominantColorFromImage
import com.example.besinkitabi.util.gorselIndir
import com.example.besinkitabi.util.placeHolderYap
import com.example.besinkitabi.viewmodel.BesinDetayViewModel


class BesinDetayFragment : Fragment() {
    private var _binding: FragmentBesinDetayBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : BesinDetayViewModel
    var besinId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBesinDetayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[BesinDetayViewModel::class.java]

        arguments?.let {
            besinId = BesinDetayFragmentArgs.fromBundle(it).besinId
        }

        viewModel.roomVerisiniAl(besinId)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner){
            binding.txtIsim.text          = it.besinIsim
            binding.txtYag.text           = it.besinYag
            binding.txtKalori.text        = it.besinKalori
            binding.txtKarbonhidrat.text  = it.besinKarbonhidrat
            binding.txtProtein.text       = it.besinProtein
            binding.imageView.gorselIndir(it.besinGorsel, placeHolderYap(requireContext()))

            getDominantColorFromImage(requireContext(), it.besinGorsel) { dominantColor ->
                // Dominant renk alındıktan sonra, koyu bir varyasyon oluşturuyoruz
                val darkerColor = ColorUtils.blendARGB(dominantColor, Color.BLACK, 0.5f)

                // GradientDrawable oluşturuyoruz: yukarıdan aşağıya geçişli
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.BOTTOM_TOP,
                    intArrayOf(dominantColor, darkerColor)
                )
                val luminance = ColorUtils.calculateLuminance(dominantColor)

                // Eğer luminance değeri 0.5'ten düşükse, koyu renk, yüksekse açık renk
                if (luminance < 0.5) {
                    // Koyu dominant renk için yazıları açık yap
                    binding.txtIsim.setTextColor(Color.LTGRAY)
                    binding.txtYag.setTextColor(Color.LTGRAY)
                    binding.txtKalori.setTextColor(Color.LTGRAY)
                    binding.txtKarbonhidrat.setTextColor(Color.LTGRAY)
                    binding.txtProtein.setTextColor(Color.LTGRAY)
                } else {
                    // Açık dominant renk için yazıları koyu yap
                    binding.txtIsim.setTextColor(Color.BLACK)
                    binding.txtYag.setTextColor(Color.BLACK)
                    binding.txtKalori.setTextColor(Color.BLACK)
                    binding.txtKarbonhidrat.setTextColor(Color.BLACK)
                    binding.txtProtein.setTextColor(Color.BLACK)
                }

                // Gradient geçişinin düzgün görünmesi için belirli bir genişlik ve yükseklik ayarlayabiliriz.
                gradientDrawable.cornerRadius = 0f // köşelerin yuvarlatılmaması için
                gradientDrawable.setSize(binding.root.width, binding.root.height)

                // Fragment'ın root view'una gradient'i uyguluyoruz:
                binding.root.background = gradientDrawable
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}