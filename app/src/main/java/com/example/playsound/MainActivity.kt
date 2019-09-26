package com.example.playsound

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.content.Context.AUDIO_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.KeyEvent


class MainActivity : AppCompatActivity() {

    var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val audioManager =
            applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.mediaMaxVolume
        audioManager.setMediaVolume(maxVolume)

        mp = MediaPlayer.create(this, R.raw.abcd)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                Log.d("ChessApp", "Key Pressed: Volume Up!!!")
                return true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Log.d("ChessApp", "Key Pressed: Volume Down!!!")
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun playSong(v: View) {
        mp?.start()
    }

    fun pauseSong(v: View) {
        mp?.pause()
    }

    fun stopSong(v: View) {
        mp?.stop()
        mp = MediaPlayer.create(this, R.raw.abcd)
    }


}

fun AudioManager.setMediaVolume(volumeIndex:Int) {
    // Set media volume level
    this.setStreamVolume(
        AudioManager.STREAM_MUSIC, // Stream type
        volumeIndex, // Volume index
        AudioManager.FLAG_SHOW_UI// Flags
    )
}

val AudioManager.mediaMaxVolume:Int
    get() = this.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
