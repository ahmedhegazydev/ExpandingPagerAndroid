package com.qslll.expandingpager.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.core.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qslll.expandingpager.InfoActivity;
import com.qslll.expandingpager.R;
import com.qslll.expandingpager.model.Travel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentTop extends Fragment {

    static final String ARG_TRAVEL = "ARG_TRAVEL";
    Travel travel;

    @Bind(R.id.image) ImageView image;
    @Bind(R.id.title) TextView title;

    public static FragmentTop newInstance(Travel travel) {
        Bundle args = new Bundle();
        FragmentTop fragmentTop = new FragmentTop();
        args.putParcelable(ARG_TRAVEL, travel);
        fragmentTop.setArguments(args);
        return fragmentTop;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            travel = args.getParcelable(ARG_TRAVEL);
            Log.e("Test1.1", "onCreate: ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (travel != null) {
            Log.e("Test1.11", "onViewCreated: ");
            image.setImageResource(travel.getImage());
            title.setText(travel.getName());
        }

    }

    @SuppressWarnings("unchecked")
    private void startInfoActivity(View view, Travel travel) {
        FragmentActivity activity = getActivity();
        ActivityCompat.startActivity(activity,
            InfoActivity.newInstance(activity, travel),
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                new Pair<>(view, getString(R.string.transition_image)))
                .toBundle());
    }
}
