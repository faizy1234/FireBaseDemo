package com.example.firebasedemo.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebasedemo.data.model.UserData;
import com.example.firebasedemo.data.repo.AuthRepo;
import com.example.firebasedemo.utils.BaseResponse;

public class LoginViewModel extends ViewModel {

    private AuthRepo authRepo = new AuthRepo();
    public LiveData<BaseResponse<UserData>> loginUser = authRepo.loginUser;


    public void login(UserData userData){

        authRepo.loginUser(userData);
    }
}
