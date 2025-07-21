package com.cinnamoroll.wallpaperlivewallpaperauth2.ui.fr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cinnamoroll.wallpaperlivewallpaperauth2.adapters.CategoryAdapter;
import com.cinnamoroll.wallpaperlivewallpaperauth2.adapters.FeaturedAdapter;
import com.cinnamoroll.wallpaperlivewallpaperauth2.databinding.FragmentDiscoverBinding;
import com.cinnamoroll.wallpaperlivewallpaperauth2.models.CategoryModel;
import com.cinnamoroll.wallpaperlivewallpaperauth2.models.FeaturedModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscoverFragment extends Fragment {
    FragmentDiscoverBinding binding;
    List<CategoryModel> allCategories = new ArrayList<>();
    List<CategoryModel> filteredCategories = new ArrayList<>();
    List<FeaturedModel> carouselWallpapers = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    FeaturedAdapter carouselAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupSearchFunctionality();
        loadCarouselData();
        loadCategoriesData();
    }

    private void setupSearchFunctionality() {
        binding.searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCategories(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterCategories(String query) {
        if (query.isEmpty()) {
            filteredCategories.clear();
            filteredCategories.addAll(allCategories);
        } else {
            filteredCategories.clear();
            for (CategoryModel category : allCategories) {
                if (category.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredCategories.add(category);
                }
            }
        }
        
        if (categoryAdapter != null) {
            categoryAdapter.updateCategories(filteredCategories);
        }
    }

    private void loadCarouselData() {
        // Load real wallpapers from the same source as main wallpapers
        new LoadCarouselWallpapers().execute();
    }

    public class LoadCarouselWallpapers extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(MyUtils.NicheLink).userAgent("firefox").followRedirects(false).get();
                Elements elements = doc.select("div#albumwp").select("div.wallpaper");

                for (Element element : elements) {
                    String img = element.select("img.wimg").attr("src");
                    if (carouselWallpapers.size() < 10) { // Limit to 10 wallpapers for carousel
                        carouselWallpapers.add(new FeaturedModel("https://wallpapercave.com" + img, false));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (isAdded()) {
                carouselAdapter = new FeaturedAdapter(getActivity(), carouselWallpapers);
                binding.carouselRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                binding.carouselRecycler.setAdapter(carouselAdapter);
            }
        }
    }

    private void loadCategoriesData() {
        new getCategoriesData().execute();
    }

    public class getCategoriesData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://wallpapercave.com/categories").userAgent("opera").get();
                Elements elements = doc.select("div#content").select("ul#catsinbox").select("li");

                for (Element element : elements) {
                    String categoryName = element.select("a").text();
                    String categoryUrl = element.select("a").attr("href");
                    if (!categoryName.contains("Religion") && !categoryName.contains("Fortnite")) {
                        allCategories.add(new CategoryModel(categoryName, categoryUrl));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (isAdded()) {
                filteredCategories.addAll(allCategories);
                categoryAdapter = new CategoryAdapter(requireActivity(), filteredCategories);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                binding.categoriesRecycler.setLayoutManager(gridLayoutManager);
                binding.categoriesRecycler.setAdapter(categoryAdapter);
                binding.categoriesRecycler.setVisibility(View.VISIBLE);
                binding.categoriesShimmer.setVisibility(View.GONE);
                binding.categoriesShimmer.stopShimmer();
            } else {
                Log.e("TAG", "onPostExecute: " + "Fragment not attached");
            }
        }
    }
} 