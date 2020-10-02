package com.rajaryan.sansadadarsh;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

public class Intro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        addSlide(AppIntroFragment.newInstance("Adopted Villages","Here You Will Come To Know About All The Adopted Villages.",R.drawable.village1, ContextCompat.getColor(getApplicationContext(),R.color.bg_screen1)));
        addSlide(AppIntroFragment.newInstance("Schemes","Here You Will Get To Know About All Government Schemes And For Complete Details You Will Get A PDF Included.",R.drawable.scheme,ContextCompat.getColor(getApplicationContext(),R.color.bg_screen2)));
        addSlide(AppIntroFragment.newInstance("Technology","Here You Will Come To Know About All The New Technology Discovered.",R.drawable.tech,ContextCompat.getColor(getApplicationContext(),R.color.bg_screen3)));
        addSlide(AppIntroFragment.newInstance("Nodel Officers","Here You Will Come To Know About All The New Technology Discovered.",R.drawable.mp,ContextCompat.getColor(getApplicationContext(),R.color.bg_screen4)));
        addSlide(AppIntroFragment.newInstance("Member Of Parliaments","Here You Can See Details Of Our Honorable Members Of Parliament.",R.drawable.mp_images,ContextCompat.getColor(getApplicationContext(),R.color.bg_screen4)));

    }
}
