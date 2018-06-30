package pl.devant.whattoeat.presenters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import pl.devant.whattoeat.R;

public class DishDescriptionActivity extends AppCompatActivity {
    private static final String TAG = "DishDescriptionActivity";

    private LinearLayout markButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_description);

        markButton = findViewById(R.id.dishDescriptionMarkView);

        markButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            v = getLayoutInflater().from(this).inflate(R.layout.dish_description_mark_dialog, null, false);
            builder.setView(v);
            builder.setPositiveButton("Akceptuj", (dialog, which) -> {
                Log.d(TAG, "onClick: Akceptuj clicked");
            });
            builder.setNegativeButton("Anuluj", (dialog, which) -> Log.d(TAG, "onClick: Anuluj clicked"));
            final AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
