package org.mifos.mobilewallet.mifospay.payments.ui

import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.base.BaseActivity
import org.mifos.mobilewallet.mifospay.common.Constants

@AndroidEntryPoint
class SendActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)
        setToolbarTitle(getString(R.string.pay))
        showColoredBackButton(R.drawable.ic_arrow_back_black_24dp)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SendFragment>(R.id.fragment_container_view)
            }
        }
    }
}
