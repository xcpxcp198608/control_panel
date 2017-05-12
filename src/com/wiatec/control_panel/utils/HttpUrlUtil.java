package com.wiatec.control_panel.utils;

import org.apache.commons.io.output.WriterOutputStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuchengpeng on 11/05/2017.
 */
public class HttpUrlUtil {

    private ThreadPoolExecutor threadPoolExecutor;
    private OnHttpListener onHttpListener;

    public HttpUrlUtil (){
        threadPoolExecutor = new ThreadPoolExecutor(4,8,3000,
                TimeUnit.MILLISECONDS ,new ArrayBlockingQueue<Runnable>(8));
    }

    public interface OnHttpListener {
        void onSuccess(String result);
        void onFailure(String e);
    }

    public void execute(String url , OnHttpListener onHttpListener){
        this.onHttpListener = onHttpListener;
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                getHttp(url);
            }
        });
    }

    public void getHttp (String url){
        HttpURLConnection httpURLConnection = null ;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result;
        try {
            URL url1 = new URL(url);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine;
                StringBuffer stringBuffer = new StringBuffer();
                while ((readLine = bufferedReader.readLine()) != null){
                    stringBuffer.append(readLine);
                }
                result = stringBuffer.toString();
                if(onHttpListener != null){
                    onHttpListener.onSuccess(result);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onHttpListener.onFailure(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            onHttpListener.onFailure(e.getMessage());
        }finally {
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
                if(httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
