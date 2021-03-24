package com.antorparvez.firebasepushnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.antorparvez.firebasepushnotification.notification_service.APIService;
import com.antorparvez.firebasepushnotification.notification_service.Client;
import com.antorparvez.firebasepushnotification.notification_service.Data;
import com.antorparvez.firebasepushnotification.notification_service.MyResponse;
import com.antorparvez.firebasepushnotification.notification_service.NotificationSender;
import com.antorparvez.firebasepushnotification.notification_service.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn);
        editText = findViewById(R.id.token);

        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        updateToken(refreshToken);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String token = editText.getText().toString();

                 sendNotification(token,"noti", "noti msg","af");
            }
        });
    }

    private void updateToken(String refreshToken) {
     //   FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Token token1 = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child("safddasfadeasdaswewerasdfa").setValue(token1);
    }



    void sendNotification(String token, String title, String msg, String Photo) {


        Data data = new Data(title, msg, Photo);

        NotificationSender sender = new NotificationSender(data, token);

        APIService apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);

        apiService.sendNotifcation(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.d("TAG", "onResponse: HERE");
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: HERE");
            }
        });


    }
}