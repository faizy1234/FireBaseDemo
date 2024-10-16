package com.example.firebasedemo.ui.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebasedemo.data.model.UserData;
import com.example.firebasedemo.data.repo.AuthRepo;
import com.example.firebasedemo.utils.BaseResponse;

public class SignUpViewModel extends ViewModel {

    private AuthRepo authRepo = new AuthRepo();
    public LiveData<BaseResponse<UserData>> createUser = authRepo.createUser;


    public void signUpWithEmailAndPassword(UserData userData){

        authRepo.signUpWithEmailAndPassword(userData);
    }
}
