package sang.thai.tran.travelcompanion.utils;

import sang.thai.tran.travelcompanion.BuildConfig;
import sang.thai.tran.travelcompanion.model.Data;
import sang.thai.tran.travelcompanion.model.ProfessionalRecordsInfoModel;
import sang.thai.tran.travelcompanion.model.RegisterModel;
import sang.thai.tran.travelcompanion.model.UserInfo;

import static sang.thai.tran.travelcompanion.utils.AppUtils.calledFrom;

public class ApplicationSingleton {

    private static ApplicationSingleton mInstance;

    public static ApplicationSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new ApplicationSingleton();
        }
        return mInstance;
    }

    private UserInfo mUserInfo;

    public boolean isGTN() {
        return BuildConfig.APPLICATION_ID.equals("sang.thai.tran.gtn");
    }
    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Log.d("Sang", "setToken : " + token + " calledFrom: " + calledFrom());
        Token = token;
    }

    private String Token;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String userType;

    public void reset() {
        setToken(null);
        setUserInfo(null);
    }

    public RegisterModel getRegisterModel() {
        return registerModel;
    }

    public void setRegisterModel(RegisterModel registerModel) {
        this.registerModel = registerModel;
    }

    private RegisterModel registerModel;

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data registerModel) {
        this.data = registerModel;
    }

    public ProfessionalRecordsInfoModel getProfessionalRecordsInfoModel() {
        return professionalRecordsInfoModel;
    }

    public void setProfessionalRecordsInfoModel(ProfessionalRecordsInfoModel professionalRecordsInfoModel) {
        this.professionalRecordsInfoModel = professionalRecordsInfoModel;
    }

    private ProfessionalRecordsInfoModel professionalRecordsInfoModel;
}
