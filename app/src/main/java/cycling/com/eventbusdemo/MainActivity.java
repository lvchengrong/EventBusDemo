package cycling.com.eventbusdemo;

import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tv_text;
    private TextView tv_text2;
    public String string;
    private Button bt_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_text2 = (TextView) findViewById(R.id.tv_text2);
        bt_button = (Button) findViewById(R.id.bt_button);
        bt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new NetDate());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setText(Message msg){
        if (msg.what==101){
            tv_text.setText((String) msg.obj);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void text(Message msg){
            tv_text2.setText((String) msg.obj);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
