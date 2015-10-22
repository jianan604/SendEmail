package com.kingsoft.sendemail;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;

public class SmsSender {

    //usage sample:
//    public static void main(String[] args) throws Exception {
//        String appid = "5";
//        String mobile = "185XXXXXXXX";
//        String content = "XXXX功能出错，请及时修正～～～";
//        sendMessage(appid,mobile,content);
//    }

    public static void sendMessage(String appid, String mobile, String content) {
        String key = "";
        try {
            key = getMD5(content+"LptSaJh188UG1yarn0C7983WG9b15gwK");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String url = "http://sms.api.iciba.com/sms_send.php";
        String par = "";
        try {
            par = "appid="+URLEncoder.encode(appid, "UTF-8")+"&mobile="+URLEncoder.encode(mobile, "UTF-8")+"&sms_content="+URLEncoder.encode(content, "UTF-8")+"&key=";
            par = par+URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println(key);

        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Send Http POST request");
        System.out.println(par);
        // String proxyAddress = http.sendPost(url, urlParameters);

        // add reuqest header
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        con.setRequestProperty("Accept-Charset", "UTF-8");

        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);

        DataOutputStream wr;
        int responseCode = 0;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(par);
            wr.flush();
            wr.close();
            responseCode = con.getResponseCode();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("\nSending 'POST' request to URL : " + url);
        // System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = null;
        String inputLine;
        StringBuffer response = new StringBuffer();
        try {
            in = new BufferedReader(new InputStreamReader(
                    con.getInputStream(), "UTF-8"));
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(response.toString());

    }

    public static String getMD5(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        byte[] md5Bytes = md5.digest();
        String res = "";
        for (int i = 0; i < md5Bytes.length; i++){
            int temp = md5Bytes[i] & 0xFF;
            if (temp <= 0XF){
                res += "0";
            }
            res += Integer.toHexString(temp);
        }
        return res;
    }

}