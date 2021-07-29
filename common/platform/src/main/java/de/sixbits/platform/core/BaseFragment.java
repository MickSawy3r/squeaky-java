package de.sixbits.platform.core;

import android.view.View;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import de.sixbits.platform.R;

public abstract class BaseFragment extends Fragment {

    void onBackPressed() {
    }

    void showProgress() {
        progressStatus(View.VISIBLE);
    }

    void hideProgress() {
        progressStatus(View.GONE);
    }

    private void progressStatus(Integer viewStatus) {
        if (getActivity() != null) {
            if (getActivity() instanceof ContainerActivity) {
                ((ContainerActivity) getActivity()).uiBinding.progress.setVisibility(viewStatus);
            }
        }
    }


    void notify(@StringRes Integer message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    void notifyWithAction(
            @StringRes Integer message,
            @StringRes Integer actionText,
            Runnable action
    ) {
        if (getView() != null) {
            Snackbar snackBar = Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE);
            snackBar.setAction(actionText, view -> action.run());
            if (getContext() != null) {
                snackBar.setActionTextColor(ContextCompat.getColor(
                        getContext(),
                        R.color.colorTextPrimary
                ));
            }
            snackBar.show();
        }
    }
}
