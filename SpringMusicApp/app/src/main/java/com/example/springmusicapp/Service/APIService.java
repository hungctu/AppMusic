package com.example.springmusicapp.Service;

public class APIService {

    private static String url="http://192.168.224.32:8925/server/";
    public static com.example.springmusicapp.Service.DataService getdataservice(){
        return APIretrofitClient.getClient(url).create(com.example.springmusicapp.Service.DataService.class);

    }
}
