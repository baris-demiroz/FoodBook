package com.example.besinkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besinkitabi.adapter.BesinRecyclerAdapter
import com.example.besinkitabi.databinding.FragmentBesinListeBinding
import com.example.besinkitabi.viewmodel.BesinListeViewModel

class BesinListeFragment : Fragment() {
    private var _binding: FragmentBesinListeBinding? = null
    private val binding get() = _binding!!

    private val besinRecyclerAdapter = BesinRecyclerAdapter(arrayListOf()) //Boş liste verdik arraylistofta


    private lateinit var viewModel: BesinListeViewModel

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBesinListeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[BesinListeViewModel::class.java]
        viewModel.refreshData()

        binding.besinRecyclerView.layoutManager = LinearLayoutManager(requireContext()) // binding.besinRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false) yatay liste
        binding.besinRecyclerView.adapter = besinRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            //swipe edilirse
            binding.txtHataMesagi.visibility       = View.GONE
            binding.besinRecyclerView.visibility   = View.GONE
            binding.besinYukleniyor.visibility     = View.VISIBLE

            viewModel.refreshDataFromInternet()

            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner){
            besinRecyclerAdapter.besinListesiniGuncelle(it)
            binding.besinRecyclerView.visibility   = View.VISIBLE
        }

        viewModel.besinHataMesaji.observe(viewLifecycleOwner){
        if (it){
            binding.txtHataMesagi.visibility   = View.VISIBLE
            binding.besinYukleniyor.visibility = View.GONE
        }
        else {
            binding.txtHataMesagi.visibility   = View.GONE
        }

        }


        viewModel.besinYukleniyor.observe(viewLifecycleOwner){
            if (it){
                binding.txtHataMesagi.visibility     = View.GONE
                binding.besinRecyclerView.visibility = View.GONE
                binding.besinYukleniyor.visibility   = View.VISIBLE
            }
            else{
                binding.besinYukleniyor.visibility   = View.GONE
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}