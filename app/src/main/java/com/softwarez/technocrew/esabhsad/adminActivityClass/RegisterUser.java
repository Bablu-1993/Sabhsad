package com.softwarez.technocrew.esabhsad.adminActivityClass;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softwarez.technocrew.esabhsad.R;
import com.softwarez.technocrew.esabhsad.prefrence.BaseUrl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {
    TextView txtDOB,txtRName,txtRMobile,txtREmail,txtRAddress,txtRMohallah,txtRWardNo,txtRAadharNo,
            txtRVoterId;
    String dob,name,mobile,email,address,mohallah,wardNo,aadhar,voterId;
    Toolbar toolbar;
    TextView txtTitle;
    Button btnRegister;
    ProgressBar progressBar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        toolbar=findViewById(R.id.toolbar);
        txtTitle=findViewById(R.id.txtTitle);
        progressBar=findViewById(R.id.progressBar);
        txtTitle.setText("Registration");
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterUser.this,SendOTPActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRegisterUser();

            }
        });

    }

    private void NewRegisterUser() {
        name=txtRName.getText().toString();
        dob=txtDOB.getText().toString();
        mobile=txtRMobile.getText().toString();
        email=txtREmail.getText().toString();
        address=txtRAddress.getText().toString();
        mohallah=txtRMohallah.getText().toString();
        wardNo=txtRMohallah.getText().toString();
        aadhar=txtRAadharNo.getText().toString();
        voterId=txtRVoterId.getText().toString();


        if (TextUtils.isEmpty(name)){
            txtRName.setError("Please enter name");
            txtRName.requestFocus();
        }if (TextUtils.isEmpty(mobile)){
            txtRMobile.setError("Please enter mobile");
            txtRMobile.requestFocus();
        }if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtREmail.setError("Enter a valid email");
            txtREmail.requestFocus();
        }if (TextUtils.isEmpty(address)){
            txtRAddress.setError("Please enter address");
            txtRAddress.requestFocus();
        }if (TextUtils.isEmpty(mohallah)){
            txtRMohallah.setError("Please enter mohallah");
            txtRMohallah.requestFocus();
        }
        if (TextUtils.isEmpty(wardNo)){
            txtRWardNo.setError("Please enter ward no");
            txtRWardNo.requestFocus();
        }
        if (TextUtils.isEmpty(aadhar)){
            txtRAadharNo.setError("Please enter aadhar number");
            txtRAadharNo.requestFocus();
        }
        if (TextUtils.isEmpty(voterId)){
            txtRVoterId.setError("Please enter voter id");
            txtRVoterId.requestFocus();
        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, BaseUrl.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterUser.this, "User Registration Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterUser.this,SendOTPActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("RName",name);
                params.put("DoB",dob);
                params.put("RMobile",mobile);
                params.put("REmail",email);
                params.put("RAddress",address);
                params.put("Mohallah",mohallah);
                params.put("WardNo",wardNo);
                params.put("AadharNo",aadhar);
                params.put("Voterid",voterId);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void init() {
        txtRName=findViewById(R.id.txtRName);
        txtDOB=findViewById(R.id.txtDOB);
        txtRMobile=findViewById(R.id.txtRMobile);
        txtREmail=findViewById(R.id.txtREmail);
        txtRAddress=findViewById(R.id.txtRAddress);
        txtRMohallah=findViewById(R.id.txtMohallah);
        txtRWardNo=findViewById(R.id.txtWardNo);
        txtRAadharNo=findViewById(R.id.txtRAadharNo);
        txtRVoterId=findViewById(R.id.txtRVoterid);
        btnRegister=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);

    }

}