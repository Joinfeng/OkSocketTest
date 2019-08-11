package com.jian.kass.oksockettest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

import java.nio.ByteBuffer;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etmsg;
    private Context context = this;
    private IConnectionManager manager;
    private TextView tvmsg;
    private StringBuffer smsg = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvmsg = findViewById(R.id.tv_msg);
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_open).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        etmsg = findViewById(R.id.et_send);

        //连接参数设置(IP,端口号),这也是一个连接的唯一标识,不同连接,该参数中的两个值至少有其一不一样
        ConnectionInfo info = new ConnectionInfo("47.107.243.15", 8317);
        //调用OkSocket,开启这次连接的通道,拿到通道Manager
        manager = OkSocket.open(info);

        //获得当前连接通道的参配对象
//        OkSocketOptions options= manager.getOption();
        //基于当前参配对象构建一个参配建造者类
//        OkSocketOptions.Builder builder = new OkSocketOptions.Builder(options);
        //修改参配设置(其他参配请参阅类文档)

        //注册Socket行为监听器,SocketActionAdapter是回调的Simple类,其他回调方法请参阅类文档
        manager.registerReceiver(new SocketActionAdapter() {
            @Override
            public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
                smsg.append("连接成功" + "\r\n");
                runUI(smsg.toString());
            }

            @Override
            public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
                super.onSocketReadResponse(info, action, data);

                smsg.append("接收数据：" +data.getBodyBytes().toString() + "\r\n");
//                tvmsg.setText(smsg.toString());
                runUI(smsg.toString());

            }

        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_open) {
            //调用通道进行连接
            manager.connect();

        } else if (id == R.id.btn_close) {
            //
            manager.disconnect();

        } else if (id == R.id.btn_send) {
            String themsg = etmsg.getText().toString();
            manager.send(new TestSendData(themsg));

        }
    }

    /**
     *
     * @param s
     */
    private void runUI(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvmsg.setText(s);
            }
        });
    }
}
