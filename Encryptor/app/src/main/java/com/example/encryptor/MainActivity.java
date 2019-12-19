package com.example.encryptor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etInput, etOutput;
    private String publicKey = "";
    private String privateKey = "";
    private byte[] encodeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etOutput = findViewById(R.id.etOutput);
        etInput = findViewById(R.id.etInput);

        try {
            Map<String, Object> keyMap = rsa.initKey();
            publicKey = rsa.getPublicKey(keyMap);
            privateKey = rsa.getPrivateKey(keyMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void encrypt(View v){

        byte[] rsaData = etInput.getText().toString().getBytes();

        try {
            encodeData = rsa.encryptByPublicKey(rsaData, getPublicKey());
            String encodeStr = new BigInteger(1, encodeData).toString();
            etOutput.setText(encodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void decrypt(View v){

        try {
            byte[] decodeData = rsa.encryptByPrivateKey(encodeData,getPrivateKey());
            String decodeStr = new String(decodeData);
            etOutput.setText(decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPublicKey() {
        return publicKey;
    }


    public String getPrivateKey() {
        return privateKey;
    }


}