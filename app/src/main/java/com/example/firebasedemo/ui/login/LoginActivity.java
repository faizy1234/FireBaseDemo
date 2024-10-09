package com.example.firebasedemo.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.firebasedemo.R;
import com.example.firebasedemo.data.model.UserData;
import com.example.firebasedemo.ui.signup.SignUpActivity;
import com.example.firebasedemo.ui.signup.SignUpViewModel;
import com.example.firebasedemo.utils.BaseResponse;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.loginUser.observe(this, new Observer<BaseResponse<UserData>>() {
            @Override
            public void onChanged(BaseResponse<UserData> userDataBaseResponse) {
                switch (userDataBaseResponse.getStatus()){
                    case ONLOADING:
                        Toast.makeText(LoginActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case ONSUCCESS:
                        Toast.makeText(LoginActivity.this, "id " +userDataBaseResponse.getData().getuId(), Toast.LENGTH_SHORT).show();
                        break;
                    case ONERROR:
                        Toast.makeText(LoginActivity.this, userDataBaseResponse.getError(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailET =  findViewById(R.id.emailET);
                EditText passwordET =  findViewById(R.id.passwordET);
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                UserData userData = new UserData();
                userData.setEmail(email);
                userData.setPassword(password);

                loginViewModel.login(userData);
            }
        });
    }
}