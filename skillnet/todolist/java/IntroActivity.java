package my.skillnet.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void openTodoList(View view) {
        Intent intent = new Intent(getApplicationContext(), TodoListActivity.class);
        startActivity(intent);
    }
}
