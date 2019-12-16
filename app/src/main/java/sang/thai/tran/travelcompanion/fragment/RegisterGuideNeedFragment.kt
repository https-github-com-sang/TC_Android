package sang.thai.tran.travelcompanion.fragment

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_register_flight_need.*
import kotlinx.android.synthetic.main.fragment_register_flight_need.tv_register_service_more
import kotlinx.android.synthetic.main.fragment_register_guide_need.*
import sang.thai.tran.travelcompanion.R
import sang.thai.tran.travelcompanion.model.RegisterModel

class RegisterGuideNeedFragment : RegisterFlightNeedFragment() {

    override fun layoutId(): Int {
        return R.layout.fragment_register_guide_need
    }

    companion object {
        fun newInstance(): RegisterGuideNeedFragment {

            return RegisterGuideNeedFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        email_sign_in_button.setOnClickListener { registerApi() }
    }

    override fun addMoreService(registerModel : RegisterModel) {
        super.addMoreService(registerModel)
        registerModel.additionalServices = tv_register_service_more?.text.toString()
        registerModel.note = et_msg?.text
        registerModel.visitPlaces = et_destination?.text
    }
}
