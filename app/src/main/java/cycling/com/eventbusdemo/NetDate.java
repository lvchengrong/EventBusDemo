package cycling.com.eventbusdemo;

import android.os.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by chengrong on 2017/2/19.
 */

public class NetDate {

    String date = "我是返回数据";

    NetDate(){
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getDate(NetDate netDate){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Message msg = Message.obtain();
                    msg.obj = date;
                    msg.what = 100;
                    EventBus.getDefault().post(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
