package com.jian.kass.oksockettest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jian.kass.oksockettest.netty.NettyClient;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

public class NettyActivity extends AppCompatActivity implements View.OnClickListener {

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



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_open) {
            NettyClient.getInstance().connect();

        } else if (id == R.id.btn_close) {
            NettyClient.getInstance().setConnectState(NettyClient.DISCONNECTION);

        } else if (id == R.id.btn_send) {


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
