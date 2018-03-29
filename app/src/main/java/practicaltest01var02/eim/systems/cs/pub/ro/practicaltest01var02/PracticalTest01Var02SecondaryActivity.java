package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {

    private Button yButton;
    private Button nButton;
    private TextView mainTextView;

    private PracticalTest01Var02SecondaryActivity.ButtonClickListener buttonClickListener = new PracticalTest01Var02SecondaryActivity.ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {


            switch(view.getId()) {
                case R.id.yButton:
                    setResult(RESULT_OK, null);
                    finish();
                    break;
                case R.id.nButton:
                    setResult(RESULT_CANCELED, null);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainTextView = (TextView)findViewById(R.id.textView3);
        String last = getIntent().getStringExtra("LAST_OPERATION");
        mainTextView.setText(last);


        yButton = (Button)findViewById(R.id.yButton);
        yButton.setOnClickListener(buttonClickListener);
        nButton = (Button)findViewById(R.id.nButton);
        nButton.setOnClickListener(buttonClickListener);

    }

}
