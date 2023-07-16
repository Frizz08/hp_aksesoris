package com.example.hp_aksesoris.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hp_aksesoris.R
import com.example.hp_aksesoris.application.AccessorisApp
import com.example.hp_aksesoris.databinding.FragmentSecondBinding
import com.example.hp_aksesoris.model.Accessoris
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val accessorisViewModel: AccessorisViewModel by viewModels {
        AccessorisViewModelFactory((applicationContext as AccessorisApp).repository)
    }
    private val args: SecondFragmentArgs by navArgs()
    private var accessoris: Accessoris?= null
    private lateinit var mMap: GoogleMap
    private var curretLatLang: LatLng?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext= requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessoris= args.accessoris
        //cek accessoris null tampilan default
        //jika tidak null tampilan sedikit berubah

        if (accessoris !=null){
            binding.deleteButton.visibility= View.VISIBLE
            binding.saveButton.text= "Ubah"
            binding.nameEditText.setText(accessoris?.name)
            binding.addressEditText.setText(accessoris?.address)
        }

        //binding google map
        val mapFragment= childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty()){
                Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()){
                Toast.makeText(context, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else{
                if (accessoris== null){
                    val accessoris= Accessoris(0, name.toString(), address.toString())
                    accessorisViewModel.insert(accessoris)
                }else{
                    val accessoris= Accessoris(accessoris?.id!!, name.toString(), address.toString())
                    accessorisViewModel.update(accessoris)
                }
            }
            findNavController().popBackStack() // Untuk dismiss halaman ini
        }

        binding.deleteButton.setOnClickListener {
            accessoris?.let { accessorisViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap= googleMap
        //implement drag marker

        val uiSettings= mMap.uiSettings
        uiSettings.isZoomControlsEnabled= true
        val sydney= LatLng(-34.8, 151.0)
        val markerOption= MarkerOptions()
            .position(sydney)
            .title("Test")
            .draggable(true)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hp_shop))
        mMap.addMarker(markerOption)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
        mMap.setOnMarkerDragListener(this)
    }

    override fun onMarkerDrag(p0: Marker) {}

    override fun onMarkerDragEnd(marker: Marker) {
        val newPosition= marker.position
        curretLatLang= LatLng(newPosition.latitude, newPosition.longitude)
        Toast.makeText(context, curretLatLang.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDragStart(p0: Marker) {}
}