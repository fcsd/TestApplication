package com.eidea.testapplication.presentation_layer.sign_in

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eidea.testapplication.R
import com.eidea.testapplication.data_layer.AppScope
import com.eidea.testapplication.data_layer.data_source.DataSourceListener
import com.eidea.testapplication.data_layer.data_source.SignInDataSource
import com.eidea.testapplication.data_layer.model.User
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    private var signInListener: SignInListener? = null
    private var signInDataSourceListener: DataSourceListener<User>
    private var sighInDataSource: SignInDataSource = SignInDataSource()

    init {
        signInDataSourceListener = object : DataSourceListener<User> {
            override fun notifyWillLoadItems() {

            }

            override fun notifyDidLoadItems(data: User?) {
                data?.let {
                    AppScope.getAccountController().saveUser(data)
                }
                signInListener?.onSigned()
            }

            override fun notifyDidLoadItemsWithError(error: String) {
                Toast.makeText(this@SignInFragment.context,
                        this@SignInFragment.context.getString(
                                R.string.default_error_text_with_message,
                                error),
                        Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        sighInDataSource.removeListener(signInDataSourceListener)

        super.onDestroyView()
    }

    fun setSignInListener(listener: SignInListener) {
        this.signInListener = listener
    }

    private fun setupViews() {
        sign_in_button.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val user = User()
        user.email = email_edit_text.text.toString()
        user.password = password_edit_text.text.toString()

        sighInDataSource.user = user
        sighInDataSource.reloadData()
        sighInDataSource.addListener(signInDataSourceListener)
    }

    companion object {
        val TAG: String = "SignInFragment"
    }

}
