package org.mistu.android.androidlab.screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.mistu.android.androidlab.MyApplication;
import org.mistu.android.androidlab.R;
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

    private UserListAdapter userListAdapter;

    private FirebaseAPIService firebaseAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule())
                .myApplicationComponent(MyApplication.get(this).getMyApplicationComponent())
                .build();
        userListAdapter = mainActivityComponent.getUserListAdapter();
        firebaseAPIService = mainActivityComponent.getFirebaseAPIService();
        setToolbar();
        setFab();
        setRecyclerView();
    }

    private void setFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAPIService.getAllUsers().enqueue(new Callback<Map<String, User>>() {
                    @Override
                    public void onResponse(@NonNull Call<Map<String, User>> call, @NonNull Response<Map<String, User>> response) {
                        if (response.body() != null) {
                            userListAdapter.resetUserList(extractUserList(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Map<String, User>> call, @NonNull Throwable t) {
                        Timber.i(String.valueOf(t.getMessage()));
                    }

                    private List<User> extractUserList(Map<String, User> responseMap) {
                        List<User> userList = new ArrayList<>();
                        for (Map.Entry<String, User> entry : responseMap.entrySet()) {
                            userList.add(entry.getValue());
                        }
                        Timber.i(userList.toString());
                        return userList;
                    }
                });
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userListAdapter);
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
