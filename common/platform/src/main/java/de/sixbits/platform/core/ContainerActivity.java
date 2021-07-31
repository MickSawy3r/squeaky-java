package de.sixbits.platform.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.sixbits.platform.databinding.ActivityLayoutBinding;

public abstract class ContainerActivity extends AppCompatActivity {
    ActivityLayoutBinding uiBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBinding = ActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(uiBinding.getRoot());
        replaceFragment(savedInstanceState);
    }

    public void replaceFragment(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        uiBinding.fragmentContainer.getId(),
                        fragment()
                ).commit();
    }

    public void replaceFragment(BaseFragment newFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        uiBinding.fragmentContainer.getId(),
                        fragment()
                ).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(
                uiBinding.fragmentContainer.getId()
        );
        if (fragment instanceof BaseFragment) {
            ((BaseFragment) fragment).onBackPressed();
        }
        super.onBackPressed();
    }

    public abstract BaseFragment fragment();
}
