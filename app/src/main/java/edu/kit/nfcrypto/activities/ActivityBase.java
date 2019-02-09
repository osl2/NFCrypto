package edu.kit.nfcrypto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import edu.kit.nfcrypto.R;

public class ActivityBase extends AppCompatActivity { //Grundactivity die allen anderen Activities die Toolbar vererbt.
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.app_bar_main);
        toolbar = findViewById(R.id.app_bar_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void setContentView(int layout) {
        View view = View.inflate(this, layout, null);
        LinearLayout linearLayout = findViewById(R.id.container);
        linearLayout.addView(view);


    }

    /**
     * Backend für den Zurückpfeil in der Toolbar
     * @param item Activity an der wir gerade sind.
     * @return Activity auf die der Zurückpfeil zeigt.
     */
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     * @return Toolbar die ActivityBase erzeugt.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }
}
