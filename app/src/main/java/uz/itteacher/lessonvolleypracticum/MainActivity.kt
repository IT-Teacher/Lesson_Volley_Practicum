package uz.itteacher.lessonvolleypracticum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.itteacher.lessonvolleypracticum.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.mainac,HomeFragment())
            .commit()

    }
}