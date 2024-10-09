package com.example.firebasedemo.data.repo;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebasedemo.data.model.UserData;
import com.example.firebasedemo.utils.BaseResponse;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepo  {
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<BaseResponse<UserData>> _createUser = new MutableLiveData<>();
    public LiveData<BaseResponse<UserData>> createUser = _createUser;

    private MutableLiveData<BaseResponse<UserData>> _loginUser = new MutableLiveData<>();
    public LiveData<BaseResponse<UserData>> loginUser = _loginUser;

    public AuthRepo(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void signUpWithEmailAndPassword(UserData userData){
        _createUser.postValue(BaseResponse.loading());

        firebaseAuth.createUserWithEmailAndPassword(userData.getEmail() , userData.getPassword())
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()){

                        UserData taskData = new UserData();
                        taskData.setuId(task.getResult().getUser().getUid());

                        _createUser.postValue(BaseResponse.success(taskData));
                    } else {
                        _createUser.postValue(BaseResponse.error(task.getException().getLocalizedMessage()));
                    }
                });

    }

    public void loginUser(UserData userData) {
        _loginUser.postValue(BaseResponse.loading());

        firebaseAuth.signInWithEmailAndPassword(userData.getEmail(), userData.getPassword())
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {

                        UserData taskData = new UserData();
                        taskData.setuId(task.getResult().getUser().getUid());

                        _loginUser.postValue(BaseResponse.success(taskData));
                    } else {
                        _loginUser.postValue(BaseResponse.error(task.getException().getLocalizedMessage()));
                    }
                });

    }

}
