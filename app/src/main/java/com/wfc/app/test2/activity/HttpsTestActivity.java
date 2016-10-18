package com.wfc.app.test2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wfc.app.test2.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class HttpsTestActivity
        extends AppCompatActivity {
    private final static String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        String url = "https://192.168.2.104:8443/api/user/getTalentList.htm";
//        String url = "https://192.168.2.104:8443/test/test1.htm";
        String url = "http://192.168.2.104:8080/test2/test1.htm";
        setCertificates(getResources().openRawResource(R.raw.mykey));
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, response);
                    }
                });
    }

    public void setCertificates(InputStream... certificates)
    {
        try
        {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            OkHttpClient client =
                    new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .build();
            OkHttpUtils.initClient(client);

        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
