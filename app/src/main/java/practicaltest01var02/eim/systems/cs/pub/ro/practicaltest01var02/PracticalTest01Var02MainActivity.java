package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {

    private Button plusButton;
    private Button minusButton;
    private Button secondaryActivityButton;
    private EditText upEditText;
    private EditText downEditText;
    private TextView mainTextView;
    private String lastOperation;

    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int upNumber = Integer.valueOf(upEditText.getText().toString());
            int downNumber = Integer.valueOf(downEditText.getText().toString());

            switch(view.getId()) {
                case R.id.plus_button:
                    mainTextView.setText(String.valueOf(upNumber) + "+" + String.valueOf(downNumber) + "=" + String.valueOf(upNumber + downNumber));
                    break;
                case R.id.minus_button:
                    mainTextView.setText(String.valueOf(upNumber) + "-" + String.valueOf(downNumber) + "=" + String.valueOf(upNumber - downNumber));
                    break;

                case R.id.secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02SecondaryActivity.class);
                    intent.putExtra("LAST_OPERATION", mainTextView.getText());
                    startActivityForResult(intent, 1);
                    break;
            }

        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BROADC", intent.getStringExtra("BROADK"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        lastOperation = "";

        plusButton = (Button)findViewById(R.id.plus_button);
        plusButton.setOnClickListener(buttonClickListener);

        minusButton = (Button)findViewById(R.id.minus_button);
        minusButton.setOnClickListener(buttonClickListener);

        secondaryActivityButton = (Button)findViewById(R.id.secondary_activity_button);
        secondaryActivityButton.setOnClickListener(buttonClickListener);

        upEditText = (EditText)findViewById(R.id.up_edit_text);
        downEditText = (EditText)findViewById(R.id.down_edit_text);
        mainTextView = (TextView) findViewById(R.id.textView);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("UP_VALUE")) {
                upEditText.setText(savedInstanceState.getString("UP_VALUE"));
            } else {
                upEditText.setText(String.valueOf(0));
            }
            if (savedInstanceState.containsKey("DOWN_VALUE")) {
                downEditText.setText(savedInstanceState.getString("DOWN_VALUE"));
            } else {
                downEditText.setText(String.valueOf(0));
            }
        } else {
            upEditText.setText(String.valueOf(0));
            downEditText.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("UP_VALUE", upEditText.getText().toString());
        savedInstanceState.putString("DOWN_VALUE", downEditText.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
        }
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "CANCELED", Toast.LENGTH_LONG).show();
        }
    }
}
