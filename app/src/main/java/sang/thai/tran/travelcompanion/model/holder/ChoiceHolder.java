package sang.thai.tran.travelcompanion.model.holder;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import sang.thai.tran.travelcompanion.R;

public class ChoiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.cb_service_pkg)
    public AppCompatCheckBox cb_service_pkg;

    @BindView(R.id.tv_service_pkg)
    public AppCompatTextView tv_service_pkg;

    public ChoiceHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {
    }

}