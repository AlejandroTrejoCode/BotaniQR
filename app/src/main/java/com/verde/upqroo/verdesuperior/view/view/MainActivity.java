package com.verde.upqroo.verdesuperior.view.view;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.fragments.CameraFragment;
import com.verde.upqroo.verdesuperior.view.view.fragments.ExploreFragment;
import com.verde.upqroo.verdesuperior.view.view.fragments.HomeFragment;
import com.verde.upqroo.verdesuperior.view.view.fragments.IrrigationFragment;
import com.verde.upqroo.verdesuperior.view.view.fragments.ReportFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = findViewById(R.id.BottomBar);
        bottomBar.setDefaultTab(R.id.home);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.home:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer, homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit()
                        ;
                        break;

                    case R.id.explore:
                        ExploreFragment exploreFragment = new ExploreFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer, exploreFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit()
                        ;
                        break;

                    case R.id.camera:
                        CameraFragment cameraFragment = new CameraFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer, cameraFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit()
                        ;
                        break;

                    case R.id.irrigation:
                        IrrigationFragment irrigationFragment = new IrrigationFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer, irrigationFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit()
                        ;
                        break;

                    case R.id.report:
                        ReportFragment reportFragment = new ReportFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer, reportFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit()
                        ;
                        break;
                }
            }
        });

    }
}
