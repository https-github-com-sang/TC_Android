package sang.thai.tran.travelcompanion.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_register_hourly_service.*
import kotlinx.android.synthetic.main.fragment_register_well_companion.*
import kotlinx.android.synthetic.main.layout_back_next.*
import kotlinx.android.synthetic.main.layout_base_medical_certificate.*
import sang.thai.tran.travelcompanion.R
import sang.thai.tran.travelcompanion.activity.MainActivity
import sang.thai.tran.travelcompanion.model.ProfessionalRecordsInfoModel
import sang.thai.tran.travelcompanion.model.Response
import sang.thai.tran.travelcompanion.retrofit.BaseObserver
import sang.thai.tran.travelcompanion.retrofit.HttpRetrofitClientBase
import sang.thai.tran.travelcompanion.utils.AppConstant
import sang.thai.tran.travelcompanion.utils.AppConstant.API_GET_PROFESSIONAL_RECORD
import sang.thai.tran.travelcompanion.utils.ApplicationSingleton
import sang.thai.tran.travelcompanion.utils.DialogUtils
import sang.thai.tran.travelcompanion.interfaces.ResultMultiChoiceDialog as ResultMultiChoiceDialog1


class RegisterWellCompanionFragment : BaseFragment() {

    override fun layoutId(): Int {
        return R.layout.fragment_register_well_companion
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_next.setOnClickListener {
            if (!checkConditionToNext()) {
                showWarningDialog(R.string.label_input_info)
                return@setOnClickListener
            }
            (activity as MainActivity).replaceFragment(R.id.fl_content, RegisterWellCompanionFragmentPage2())
        }

        btn_back.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        controlMedicalCertificationUI(sw_certification.isChecked)
        getProfessionalRecords(API_GET_PROFESSIONAL_RECORD)
        sw_certification.setOnCheckedChangeListener { _, b ->
            controlMedicalCertificationUI(b)
        }

        if (ApplicationSingleton.getInstance().professionalRecordsInfoModel == null) {
            ApplicationSingleton.getInstance().professionalRecordsInfoModel = ProfessionalRecordsInfoModel()
        }
    }

    //    GET /api/SelectList/getProfessionalRecords
    private fun getProfessionalRecords(url: String) {
        if (activity == null || isMultiClicked()) {
            return
        }
        HttpRetrofitClientBase.getInstance().executeGet(url,
                ApplicationSingleton.getInstance().token, object : BaseObserver<Response>(true) {
            override fun onSuccess(result: Response, response: String) {
                hideProgressDialog()
                if (activity == null) {
                    return
                }
                if (result.statusCode == AppConstant.SUCCESS_CODE) {
                    ApplicationSingleton.getInstance().data = result.result?.data

                    activity?.runOnUiThread {
                        if (tv_professional_qualification != null)
                            setOnClickAndShowDialog(
                                    tv_professional_qualification,
                                    ApplicationSingleton.getInstance().data.degreesList!!,
                                    object : ResultMultiChoiceDialog1 {
                                        override fun getListSelectedItem(list: List<String>) {
                                            ApplicationSingleton.getInstance().professionalRecordsInfoModel?.professional_Degree_List = list
                                        }
                                    }
                            )
                        if (tv_specialized != null)
                            setOnClickAndShowDialog(tv_specialized, ApplicationSingleton.getInstance().data.qualificationList!!,
                                    object : ResultMultiChoiceDialog1 {
                                        override fun getListSelectedItem(list: List<String>) {
                                            ApplicationSingleton.getInstance().professionalRecordsInfoModel?.qualification_List = list
                                        }
                                    })
                        if (tv_communication_level != null)
                            setOnClickAndShowDialog(tv_communication_level, ApplicationSingleton.getInstance().data.communicationSkillsList!!,
                                    object : ResultMultiChoiceDialog1 {
                                        override fun getListSelectedItem(list: List<String>) {
                                            ApplicationSingleton.getInstance().professionalRecordsInfoModel?.communication_Skills = list.get(0)
                                        }
                                    })
                    }
                } else {
                    activity?.runOnUiThread { DialogUtils.showAlertDialog(activity, result.message) { dialog, _ -> dialog.dismiss() } }
                }
            }

            override fun onFailure(e: Throwable, errorMsg: String) {
                hideProgressDialog()
                if (!TextUtils.isEmpty(errorMsg)) {
                    activity?.runOnUiThread { DialogUtils.showAlertDialog(activity, errorMsg) { dialog, _ -> dialog.dismiss() } }
                }
            }
        })
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(tv_register_service_more?.windowToken, 0)
    }

    private fun controlMedicalCertificationUI(on: Boolean) {
        if (on) {
            ApplicationSingleton.getInstance().professionalRecordsInfoModel?.medical_Certificate = getString(R.string.label_yes)
            et_give_basic_media_cer.visibility = View.VISIBLE
            ll_join_basic_media_cer.visibility = View.GONE
        } else {
            et_give_basic_media_cer.visibility = View.GONE
            ll_join_basic_media_cer.visibility = View.VISIBLE
            ApplicationSingleton.getInstance().professionalRecordsInfoModel?.medical_Certificate = getString(R.string.label_no)
        }
    }

    internal fun openDepartureDate() {
        if (activity == null) {
            return
        }
        activity!!.onBackPressed()
    }

    private fun checkConditionToNext(): Boolean {
        // bang cap
        if (activity?.getText(R.string.label_professional_qualification)!! == tv_professional_qualification.text) {
            return false
        }

        if (activity?.getText(R.string.label_specialized)!! == tv_specialized.text) {
            return false
        }

        if (TextUtils.isEmpty(et_other_specialized.text)) {
            return false
        }

        if (TextUtils.isEmpty(et_foreign_language.text)) {
            return false
        }

        if (activity?.getText(R.string.label_communication_skills)!! == tv_communication_level.text) {
            return false
        }

        if (TextUtils.isEmpty(tv_current_job.text)) {
            return false
        }

        if (TextUtils.isEmpty(et_working_place.text)) {
            return false
        }

        if (TextUtils.isEmpty(et_working_position.text)) {
            return false
        }
        if (sw_certification.isChecked && TextUtils.isEmpty(et_give_basic_media_cer.text)) {
            return false
        }

        return true
    }

    companion object {
        fun newInstance(): RegisterWellCompanionFragment {
            return RegisterWellCompanionFragment()
        }
    }

}
