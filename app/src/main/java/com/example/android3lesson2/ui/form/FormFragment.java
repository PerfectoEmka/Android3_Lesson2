package com.example.android3lesson2.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson2.App;
import com.example.android3lesson2.R;
import com.example.android3lesson2.data.models.Post;
import com.example.android3lesson2.databinding.FragmentFormBinding;
import com.example.android3lesson2.databinding.FragmentPostBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;

    public FormFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnCreatePost.setOnClickListener(v -> {
            Post post = new Post(
                    binding.etTitle.getText().toString(),
                    binding.etDescription.getText().toString(),
                    Integer.parseInt(binding.etUserId.getText().toString()),
                    35
            );
            App.api.createPost(post).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()){
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                        navController.navigateUp();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });
    }
}