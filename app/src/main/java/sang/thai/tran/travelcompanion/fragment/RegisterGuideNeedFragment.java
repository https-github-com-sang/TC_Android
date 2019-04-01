package sang.thai.tran.travelcompanion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sang.thai.tran.travelcompanion.R;
import sang.thai.tran.travelcompanion.adapter.RVAdapterChoiceMulti;
import sang.thai.tran.travelcompanion.model.ItemOptionModel;
import sang.thai.tran.travelcompanion.view.EditTextViewLayout;

public class RegisterGuideNeedFragment extends BaseFragment {

    @BindView(R.id.et_date)
    EditTextViewLayout et_date;

    @BindView(R.id.et_hour)
    EditTextViewLayout et_hour;

    @BindView(R.id.et_flight_number)
    EditTextViewLayout et_flight_number;

    @BindView(R.id.et_airline)
    EditTextViewLayout et_airline;

    @BindView(R.id.et_airport_departure)
    EditTextViewLayout et_airport_departure;

    @BindView(R.id.et_arrival_airport)
    EditTextViewLayout et_arrival_airport;

    @Nullable
    @BindView(R.id.rv_service_pkg)
    RecyclerView rv_service_pkg;

    @BindView(R.id.email_sign_in_button)
    Button email_sign_in_button;

    private List<ItemOptionModel> itemOptionModelList = new ArrayList<>();

    public static RegisterGuideNeedFragment newInstance(boolean update) {
        RegisterGuideNeedFragment infoRegisterFragment = new RegisterGuideNeedFragment();
        return infoRegisterFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (rv_service_pkg != null) {
            RVAdapterChoiceMulti rvAdapterChoiceMulti = new RVAdapterChoiceMulti(getActivity());
            rv_service_pkg.setAdapter(rvAdapterChoiceMulti);
//        rv_service_pkg.setItemInfoList
            for (String string : getResources().getStringArray(R.array.service_pkg_list)) {
                ItemOptionModel itemOptionModel = new ItemOptionModel();
                itemOptionModel.setChecked(false);
                itemOptionModel.setService(string);
                itemOptionModelList.add(itemOptionModel);
            }
            rvAdapterChoiceMulti.setItemInfoList(itemOptionModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            rv_service_pkg.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_register_guide_need;
    }


    @OnClick(R.id.email_sign_in_button)
    protected void register() {
        if (getActivity() != null) {
        }
    }

}