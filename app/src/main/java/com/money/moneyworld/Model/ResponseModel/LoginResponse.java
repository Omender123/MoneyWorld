package com.money.moneyworld.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("APICODERESULT")
    @Expose
    private String apicoderesult;
    @SerializedName("logindetails")
    @Expose
    private Logindetails logindetails;

   
    public LoginResponse() {
    }

    public LoginResponse(Integer statusCode, String apicoderesult, Logindetails logindetails) {
        super();
        this.statusCode = statusCode;
        this.apicoderesult = apicoderesult;
        this.logindetails = logindetails;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getApicoderesult() {
        return apicoderesult;
    }

    public void setApicoderesult(String apicoderesult) {
        this.apicoderesult = apicoderesult;
    }

    public Logindetails getLogindetails() {
        return logindetails;
    }

    public void setLogindetails(Logindetails logindetails) {
        this.logindetails = logindetails;
    }

    public static class Logindetails {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("createdDtm")
        @Expose
        private String createdDtm;


        public Logindetails(String userId, String name, String email, String mobile) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.mobile = mobile;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedDtm() {
            return createdDtm;
        }

        public void setCreatedDtm(String createdDtm) {
            this.createdDtm = createdDtm;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Logindetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("userId");
            sb.append('=');
            sb.append(((this.userId == null) ? "<null>" : this.userId));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null) ? "<null>" : this.name));
            sb.append(',');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null) ? "<null>" : this.email));
            sb.append(',');
            sb.append("mobile");
            sb.append('=');
            sb.append(((this.mobile == null) ? "<null>" : this.mobile));
            sb.append(',');
            sb.append("password");
            sb.append('=');
            sb.append(((this.password == null) ? "<null>" : this.password));
            sb.append(',');
            sb.append("otp");
            sb.append('=');
            sb.append(((this.otp == null) ? "<null>" : this.otp));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null) ? "<null>" : this.status));
            sb.append(',');
            sb.append("createdDtm");
            sb.append('=');
            sb.append(((this.createdDtm == null) ? "<null>" : this.createdDtm));
            sb.append(',');
            if (sb.charAt((sb.length() - 1)) == ',') {
                sb.setCharAt((sb.length() - 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }
    }
}
