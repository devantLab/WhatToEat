package pl.devant.whattoeat.presenters;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.DataViewModel;
import pl.devant.whattoeat.model.Restarant;

public class SpashScreen extends AppCompatActivity {

    private static final String TAG = "SpashScreen";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("restaurants");
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private DataViewModel mModel;
    private boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        mModel = ViewModelProviders.of(this).get(DataViewModel.class);

       



    }
}
