package com.skytop.firstproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Wrapper;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context context;

    BackgroundTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String method = strings[0];
        String email = strings[1];
        String pass = strings[2];
//        String regURL = "https://skytoptrial.000webhostapp.com/reg.php";
//        String loginURL = "https://skytoptrial.000webhostapp.com/login.php";
//        String recoverPassURL= "https://skytoptrial.000webhostapp.com/mail_pass.php";
        String regURL = "http://75ce8774.ngrok.io/skytop/reg.php";
        String loginURL= "http://75ce8774.ngrok.io/skytop/login.php";
        String recoverPassURL= "http://75ce8774.ngrok.io/skytop/mail_pass.php";
        if (method.equals("register")) {
            try {
                URL url = new URL(regURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();//flush the bufferedwriter
                bufferedWriter.close();
                os.close();//close the output stream
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                String result = "registration successful";
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            //log the user in
            try {
                URL url = new URL(loginURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //result = "login successful";
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("recover")){
            //method to recover user password using email
            try {
                URL url = new URL(recoverPassURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data =URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();//flush the bufferedwriter
                bufferedWriter.close();
                os.close();//close the output stream
                InputStream is = httpURLConnection.getInputStream();
                //String result="mail sent";
                //*** trial 1
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                //*end of trial 1
                is.close();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    protected void onPreExecute() {
        //before task executes
    }

    protected void onPostExecute(String result) {
    //upon completion
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        if(result.equals("login successful")){
            context.startActivity(new Intent(context, home.class));
        }
        if(result.equals("registration successful")){
            context.startActivity(new Intent(context, login.class));
        }
//        if(result.equals("mail sent")){
//            Toast.makeText(context, "mail has been sent", Toast.LENGTH_SHORT).show();
//        }
    }
}