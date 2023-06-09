package com.divyansh.imagedownloader

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.divyansh.imagedownloader.databinding.ImageLoaderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Imagedownloader : AppCompatActivity() {
    private lateinit  var binding : ImageLoaderBinding
    private lateinit var URL : String
    private  var permission_granted : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
                isGranted : Boolean ->
            permission_granted = isGranted
        }


        when{
            ContextCompat.checkSelfPermission(this , android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED -> {
                // permission was granted
                    permission_granted = true
                }
        else -> {
            requestPermissionLauncher.launch(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }}


        binding = ImageLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.footerMsg1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse("https://github.com/dev-divyansh/ImageDownloader"))
            startActivity(intent)
        }



        var py : Python = Python.getInstance()
        var pyObject : PyObject = py.getModule("scraper")

        binding.button.setOnClickListener {

            if (is_empty(binding.url.text.toString()) == true){
                Toast.makeText(this , "url is required" , Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            URL = binding.url.text.toString()

            CoroutineScope(Dispatchers.Main).launch {


             if (permission_granted){
                 binding.button.isClickable = false
                 var newRes  = ArrayList<String>()
                 try {
                     var res = pyObject.callAttr("main" , URL ).asList().toTypedArray()


                     for (i in res){
                         if(i != null) {
                             newRes.add(i.toString())
                         }
                     }
                 }
                 catch (e : Exception){
                     Toast.makeText(applicationContext , "Something went wrong" , Toast.LENGTH_LONG).show()
                     binding.button.isClickable = true
                     return@launch
                 }

                 downloadImages(newRes , this@Imagedownloader)
                }
                else{
                    Toast.makeText(applicationContext  , "You need to allow the request in order to download the images" , Toast.LENGTH_LONG).show()
                 requestPermissionLauncher.launch(
                     android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                 )
                }

            }
        }
    }

    private fun downloadImages(res: ArrayList<String> , context : Context) {

        //  code for downloading  the images
        binding.url.isEnabled = false
        binding.button.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE


        CoroutineScope(Dispatchers.Main).launch {
            delay(7000)
            var intent = Intent( context, ImageDisplay::class.java)

            intent.putExtra("dimage" , res)
            startActivity(intent)
            finish()

        }

    }

    fun is_empty(url : String) : Boolean{

        return url.isNullOrBlank()
    }




}