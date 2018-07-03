package pl.devant.whattoeat.presenters;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import pl.devant.whattoeat.R;

public class DishDescriptionActivity extends AppCompatActivity {
    private static final String TAG = "DishDescriptionActivity";

    private LinearLayout markButton;
    private ScaleRatingBar ratingBar;
    private float dishRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_description);

        markButton = findViewById(R.id.dishDescriptionMarkView);

        markButton.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            v = getLayoutInflater().from(this).inflate(R.layout.dish_description_mark_dialog, null, false);
            builder.setView(v);
            builder.setPositiveButton("Akceptuj", (dialog, which) -> {
                Log.d(TAG, "onClick: Akceptuj clicked");
                Toast.makeText(this, "Oceniłeś to danie na " + dishRating, Toast.LENGTH_SHORT).show();
            });
            ratingBar = v.findViewById(R.id.ratingBar);
            ratingBar.setNumStars(5);

            ratingBar.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
                @Override
                public void onRatingChange(BaseRatingBar baseRatingBar, float value) {
                    dishRating = value;
                }
            });
            builder.setNegativeButton("Anuluj", (dialog, which) -> Log.d(TAG, "onClick: Anuluj clicked"));
            final AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
