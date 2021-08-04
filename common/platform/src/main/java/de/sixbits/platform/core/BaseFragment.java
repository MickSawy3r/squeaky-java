package de.sixbits.platform.core;

import android.util.Log;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import de.sixbits.platform.R;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    public void onBackPressed() {
    }

    protected void showProgress() {
        progressStatus(View.VISIBLE);
    }

    protected void hideProgress() {
        progressStatus(View.GONE);
    }

    private void progressStatus(Integer viewStatus) {
        if (getActivity() != null & getActivity() instanceof ContainerActivity) {
            Log.d(TAG, "progressStatus: ");
            ((ContainerActivity) getActivity()).uiBinding.progress.setVisibility(viewStatus);
        }
    }


    protected void notify(@StringRes Integer message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    protected void notifyWithAction(
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
