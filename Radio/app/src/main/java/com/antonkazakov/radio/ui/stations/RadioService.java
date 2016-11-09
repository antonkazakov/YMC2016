package com.antonkazakov.radio.ui.stations;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.antonkazakov.radio.AppDelegate;
import com.antonkazakov.radio.data.content.BusEvents;
import com.antonkazakov.radio.Config;

import java.io.IOException;

public class RadioService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    private MediaPlayer player;

    private final IBinder musicBind = new RadioBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        initMusicPlayer();
    }

    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    public void playSong(String song){
        player.reset();
        try {
            player.setDataSource(song);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }

        player.prepareAsync();
        AppDelegate.getContext().getBus().send(new BusEvents.Message(Config.TAG_START));
    }

    public void pauseSong(){
        player.pause();
        AppDelegate.getContext().getBus().send(new BusEvents.Message(Config.TAG_PAUSE));
    }

    public void resumeSong(){
        player.start();
        AppDelegate.getContext().getBus().send(new BusEvents.Message(Config.TAG_DONE));
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        AppDelegate.getContext().getBus().send(new BusEvents.Message(Config.TAG_START));
        mediaPlayer.start();
    }


    public class RadioBinder extends Binder {

        RadioService getService() {
            return RadioService.this;
        }

    }


}
