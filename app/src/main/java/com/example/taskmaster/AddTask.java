package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class AddTask extends AppCompatActivity {
    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    String longitude;
    String latitude;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_task);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    Button attachFile = findViewById(R.id.uploadFile);
    attachFile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFile();
            getLastLocation();
        }
    });

    List<Team> allTeam = new ArrayList<>();
    RadioButton team1 = findViewById(R.id.radioButtonTeam1);
    RadioButton team2 = findViewById(R.id.radioButtonTeam2);
    RadioButton team3 = findViewById(R.id.radioButtonTeam3);
    Amplify.API.query(
            ModelQuery.list(Team.class),
            response -> {
                for (Team team : response.getData()) {
                    Log.i("MyAmplifyApp", team.getName());
                    allTeam.add(team);

                    System.out.println("here is the data all team " + allTeam);

                }
                Log.i("MyAmplifyApp", "outside the loop");
            },
            error -> Log.e("MyAmplifyApp", "Query failure", error)
    );

    Intent intent = getIntent();
    String action = intent.getAction();
    String getType = intent.getType();
    ImageView image = findViewById(R.id.imageViewUplod);

    if (Intent.ACTION_SEND.equals(action) && getType  != null) {

        if (getType.startsWith("image/")) {
            Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);

            if (imageUri != null) {
                image.setImageURI(imageUri);
                image.setVisibility(View.VISIBLE);
            }
        }
    }
    
    Button SubmitButton = findViewById(R.id.Submit);
    SubmitButton.setOnClickListener(new View.OnClickListener() {
        int countTasks =0 ;
        @Override
        public void onClick(View v) {
            ArrayList<String> location = new ArrayList<>();
            location.add(longitude);
            location.add(latitude);
            EditText title = findViewById(R.id.editTitle);
            String GetTitle = title.getText().toString();
            EditText body = findViewById(R.id.editBody);
            String GetBody = body.getText().toString();
            EditText state = findViewById(R.id.editState);
            String GetState = state.getText().toString();
            Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
            countTasks++;
            TextView tasksNum = findViewById(R.id.taskNum);
//            TaskModel task = new TaskModel(GetTitle,GetBody,GetState);
            tasksNum.setText(String.valueOf(countTasks));
            //AppDatabase appDb = AppDatabase.getInstance(getApplicationContext());
            // appDb.taskDao().insertAll(task);

            Intent goToHomePage = new Intent(AddTask.this, MainActivity.class);
            startActivity(goToHomePage);
            String teamName = "";
            if (team1.isChecked()) {

                teamName = team1.getText().toString();

            } else if (team2.isChecked()) {

                teamName = team2.getText().toString();


            } else if (team3.isChecked()) {

                teamName = team3.getText().toString();

            }
            Team selectedTeam = null;
            for (Team teams : allTeam) {
                if (teams.getName().equals(teamName)) {
                    selectedTeam = teams;


                }

            }



            Task tasks = Task.builder()
                    .title(title.getText().toString())
                    .body(body.getText().toString())
                    .team(selectedTeam)
                    .file(file)
                    .location(location)
                    .state(state.getText().toString())
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(tasks),
                    response2 -> Log.i("MyAmplifyApp", "Added Todo with id: " + response2.getData().getId()
                    ),

                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );

        }
    });

}
    String file = "";
    private void getFile() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a File");
        startActivityForResult(chooseFile, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFileCopied");
        try {
            byte[] buffer = new byte[1024];
            int length;
            InputStream inputStream = getContentResolver().openInputStream(data.getData());
            OutputStream outputStream = new FileOutputStream(uploadFile);

            file = data.getData().toString();


            while ((length = inputStream.read(buffer)) > 0) {

                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();


            Amplify.Storage.uploadFile(
                    "image",
                    uploadFile,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (checkPermissions()) {
//            getLastLocation();
//        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            longitude = String.valueOf(location.getLongitude());
                            latitude = String.valueOf(location.getLatitude());

                        }
                    }

                });
            }
        } else {
           requestPermissions();
        }
    }

        private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    }
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest ();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());

    }
    private LocationCallback mLocationCallback = new LocationCallback() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            longitude = String.valueOf(mLastLocation.getLongitude());
            latitude = String.valueOf(mLastLocation.getLatitude());
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

}
