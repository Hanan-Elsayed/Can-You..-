package com.example.canyou.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.pojo.SearchResponseItem;
import com.example.canyou.ui.adapter.SearchAdapter;
import com.example.canyou.viewmodel.SearchViewModel;

import java.util.List;

public class SearchFragment extends Fragment implements SearchAdapter.OnItemClick {
    private SearchViewModel searchViewModel;
    private SearchAdapter searchAdapter;
    private EditText searchEditText;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchEditText = view.findViewById(R.id.search_edit_text);
        RecyclerView searchRecyclerView = view.findViewById(R.id.search_recycler_view);
        searchAdapter = new SearchAdapter();
        searchAdapter.setOnItemClick(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.setAdapter(searchAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeSearchResults();
        setupSearchTextWatcher();
        // Observe the message live data for post creation status
        searchViewModel.getMessageLiveData().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

        });

    }

    private void observeSearchResults() {
        searchViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<SearchResponseItem>>() {
            @Override
            public void onChanged(List<SearchResponseItem> searchResults) {
                searchAdapter.setUsers(searchResults);
            }
        });
    }

    private void setupSearchTextWatcher() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PreferenceManager preferenceManager = new PreferenceManager(requireContext());
                String token = preferenceManager.getToken();
                String searchValue = s.toString();
                searchViewModel.searchUsers(token, searchValue);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });
    }

    @Override
    public void onClick(String authorId) {
        Bundle args = new Bundle();
        args.putString("authorId", authorId);
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(args);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
