package org.mistu.android.androidlab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.mistu.android.androidlab.model.User;
import org.mistu.android.androidlab.rest.FirebaseAPIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAPIService firebaseAPIService = MyApplication.get(MainActivity.this).getFirebaseAPIService();
                firebaseAPIService.getAllUsers().enqueue(new Callback<Map<String, User>>() {
                    @Override
                    public void onResponse(@NonNull Call<Map<String, User>> call, @NonNull Response<Map<String, User>> response) {
                        if (response.body() != null) {
                            for (Map.Entry<String, User> entry : response.body().entrySet()) {
                                userList.add(entry.getValue());
                            }
                            Timber.i(userList.toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Map<String, User>> call, @NonNull Throwable t) {
                        Timber.i(String.valueOf(t.getMessage()));
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
