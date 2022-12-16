package com.example.springmusicapp.Service;

import com.example.springmusicapp.Model.Advertisement;
import com.example.springmusicapp.Model.Category;
import com.example.springmusicapp.Model.Ketqua;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.Model.Singer;
import com.example.springmusicapp.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("advertisement")
    Call<List<Advertisement>> getAdData();

    @GET("randomcategory")
    Call<List<Category>> getRandomcategory();

    @GET("top10music")
    Call<List<Music>> getTopMusicData();

    @GET("randommusic")
    Call<List<Music>> getRandommusic();

    @FormUrlEncoded
    @POST("login")
    Call<List<User>> Login(@Field("username") String username, @Field("password") String pass);

    @FormUrlEncoded
    @POST("signin")
    Call<List<Ketqua>> Signin(@Field("fullname") String fname,
                              @Field("username") String usn,
                              @Field("password") String pass);

    @FormUrlEncoded
    @POST("playlist")
    Call<List<MusicList>> getplaylist(@Field("userId") int id);

    @GET("allcategory")
    Call<List<Category>> getCategory();

    @FormUrlEncoded
    @POST("baihattheotheloai")
    Call<List<Music>> baihattheotheloai(@Field("ca_id") int id);

    @FormUrlEncoded
    @POST("musicinplaylist")
    Call<List<Music>> musicinplaylist(@Field("playlistid") int id);

    @FormUrlEncoded
    @POST("timkiemcasi")
    Call<List<Singer>> timkiemcasi(@Field("singername") String timkiem);

    @FormUrlEncoded
    @POST("timkiembaihat")
    Call<List<Music>> timkiembaihat(@Field("timkiem") String timkiem);

    @FormUrlEncoded
    @POST("baihattheocasi")
    Call<List<Music>> baihattheocasi(@Field("singer_id") int id);

    @FormUrlEncoded
    @POST("taoplaylist")
    Call<List<Ketqua>> taoplaylist(@Field("userId") int user_id,
                                   @Field("musicListName") String music_list_name,
                                   @Field("musicid") int music_id);

    @FormUrlEncoded
    @POST("thembaihat")
    Call<List<Ketqua>> thembaihatpl(@Field("musicId") int music_id,
                                    @Field("musicListId") int list_id);

    @FormUrlEncoded
    @POST("xoabaihat")
    Call<List<Ketqua>> xoabaihatkhoiplaylist(@Field("musicId") int music_id,
                                             @Field("musicListId") int list_id);

    @FormUrlEncoded
    @POST("capnhatluotnghe")
    Call<List<Ketqua>> capnhatluotnghe(@Field("musicId") int music_id,
                                    @Field("userId") int user_id);

    @FormUrlEncoded
    @POST("capnhatcamxuc")
    Call<List<Ketqua>> capnhatcamxuc(@Field("musicId") int music_id,
                                     @Field("userId") int user_id,
                                     @Field("emotionId") int emotion_id);

    @FormUrlEncoded
    @POST("baihatusernghetheocamxuc")
    Call<List<Music>> baihatusernghetheocamxuc(@Field("userId") int userId,
                                               @Field("emotionId") int emotionId);

    @FormUrlEncoded
    @POST("baihatnghetheocamxuc")
    Call<List<Music>> baihatnghetheocamxuc(@Field("emotion_id") int emotionId);

    @FormUrlEncoded
    @POST("capnhatcamxuc2")
    Call<List<Ketqua>> capnhatcamxuc2(@Field("musicId") int music_id,
                                     @Field("userId") int user_id,
                                     @Field("emotionId") int emotion_id);

    @FormUrlEncoded
    @POST("baihatusernghenhieu")
    Call<List<Music>> baihatusernghenhieu(@Field("user_id") int user_id);
}
