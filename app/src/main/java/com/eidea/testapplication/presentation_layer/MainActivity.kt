package com.eidea.testapplication.presentation_layer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.eidea.testapplication.R
import com.eidea.testapplication.presentation_layer.mvp.product_page.ProductPageFragment
import com.eidea.testapplication.presentation_layer.mvp.product_page.ProductPageListener
import com.eidea.testapplication.presentation_layer.sign_in.SignInFragment
import com.eidea.testapplication.presentation_layer.sign_in.SignInListener

class MainActivity : AppCompatActivity() {

    private val productPageFragment = ProductPageFragment()
    private val signInFragment = SignInFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

        setupSignInFragment()
        setupProductPageFragment()

        setSignInFragment()
    }

    private fun setupSignInFragment() {
        signInFragment.setSignInListener(object : SignInListener {
            override fun onSigned() {
                setProductPageFragment()
//                setBagFragment()
            }
        })
    }

    private fun setupProductPageFragment() {
        productPageFragment.setProductPageListener(object : ProductPageListener {
            override fun onAddToBag(productId: Long, selectedColor: String) {
                // Add to Bag
            }
        })
    }

    private fun setProductPageFragment() {
        replaceFragment(R.id.content_main,
                productPageFragment,
                ProductPageFragment.TAG,
                false)
    }

    private fun setSignInFragment() {
        replaceFragment(R.id.content_main,
                signInFragment,
                SignInFragment.TAG,
                false)
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment, tag: String, isAddToBackStack: Boolean) {
        val fragmentManager: FragmentManager = this.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, tag)
        if (isAddToBackStack) fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        when (getFragmentTag()) {
            ProductPageFragment.TAG -> setSignInFragment()
            else -> super.onBackPressed()
        }
    }

    private fun getFragmentTag(): String {
        val tag = supportFragmentManager.findFragmentById(R.id.content_main).tag
        return tag ?: "tag is null"
    }

}
