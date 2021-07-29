package de.sixbits.platform.core;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.sixbits.platform.databinding.ActivityLayoutBinding;

public abstract class ContainerActivity extends AppCompatActivity {
    ActivityLayoutBinding uiBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBinding = ActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(uiBinding.getRoot());
        addFragment(savedInstanceState);
    }

    public void addFragment(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(
                        uiBinding.fragmentContainer.getId(),
                        fragment()
                ).commit();
    }

    public abstract BaseFragment fragment();
}
