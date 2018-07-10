package my.skillnet.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void nextPage(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivity(intent);
    }


}
