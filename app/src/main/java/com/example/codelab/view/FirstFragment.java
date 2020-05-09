package com.example.codelab.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

//import com.example.codelab.FirstFragmentDirections;
import com.example.codelab.R;

public class FirstFragment extends Fragment {

    private TextView showCountTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        // Get the count text view
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);
        // Inflate the layout for this fragment

        return fragmentFirstLayout;
    }

    @SuppressLint("SetTextI18n")
    private void countMe(View view) {
        // Get the value of the text view
        String countString = showCountTextView.getText().toString();
        // Convert value to a number and increment it
        int count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        showCountTextView.setText(Integer.toString(count));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.random_button).setOnClickListener(view1 -> {
            TextView showCountTextView = view1.getRootView().findViewById(R.id.textview_first);
            int currentCount = Integer.parseInt(showCountTextView.getText().toString());

            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action;
            action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);

            NavHostFragment.findNavController(FirstFragment.this).navigate(action);
        });

        view.findViewById(R.id.toast_button).setOnClickListener(view12 -> {
            Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
            myToast.show();
        });

        view.findViewById(R.id.count_button).setOnClickListener(this::countMe);

        view.findViewById(R.id.previous_button).setOnClickListener(view14 -> NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_ZeroFragment));

    }


}
