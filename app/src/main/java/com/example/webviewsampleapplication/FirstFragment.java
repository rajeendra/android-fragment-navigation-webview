package com.example.webviewsampleapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.webviewsampleapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    SharedPreferences sharedPref = null ;
    SharedPreferences.Editor editor = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load SharedPreferences
        sharedPref = binding.getRoot().getContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor =sharedPref.edit();

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(binding.getRoot().getContext(), binding.txtToken.getText().toString(), Toast.LENGTH_SHORT).show();
                saveToken(binding.txtToken.getText().toString());
            }
        });

        if(isTokenSaved()){
            binding.txtToken.setHint("<API-KEY> already saved. or Save a new");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void saveToken(String token){
        // store token at SharedPreferences
        if ((token != null && !token.isEmpty()) ){
            editor.putString(ApplicationConstants.TOKEN, token);
            editor.commit();
            binding.txtToken.setHint("<API-KEY> already saved. or Save a new");
        }
    }

    public boolean isTokenSaved(){
        boolean tokenSaved = false;
        String token = sharedPref.getString(ApplicationConstants.TOKEN, null);
        if ((token != null && !token.isEmpty()) ){
            tokenSaved = true;
        }
        return tokenSaved;
    }

}