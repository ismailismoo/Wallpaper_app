package com.cinnamoroll.wallpaperlivewallpaperauth2.ui.fr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;


import com.cinnamoroll.wallpaperlivewallpaperauth2.adapters.CategoryAdapter;
import com.cinnamoroll.wallpaperlivewallpaperauth2.adapters.FeaturedAdapter;
import com.cinnamoroll.wallpaperlivewallpaperauth2.databinding.FragmentCategoryBinding;
import com.cinnamoroll.wallpaperlivewallpaperauth2.models.CategoryModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriesFragment extends Fragment {
    FragmentCategoryBinding binding;
    List<CategoryModel> categoryModels = new ArrayList<>();
    CategoryAdapter categoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
                new getCategoriesData().execute();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public class getCategoriesData extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://wallpapercave.com/categories").userAgent("opera").get();
                Elements elements = doc.select("div#content").select("ul#catsinbox").select("li");

                for (Element element : elements) {
                    String categoryName = element.select("a").text();
                    String categoryUrl = element.select("a").attr("href");
                    if (!categoryName.contains("Religion") && !categoryName.contains("Fortnite")) {
                        categoryModels.add(new CategoryModel(categoryName, categoryUrl));
                    }
                }




            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (isAdded()){
                categoryAdapter = new CategoryAdapter(requireActivity(), categoryModels);
                // Use 2 columns for better visual appeal
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                binding.categoryRecycler.setLayoutManager(gridLayoutManager);
                binding.categoryRecycler.setAdapter(categoryAdapter);
                binding.categoryRecycler.setVisibility(View.VISIBLE);
                binding.WallpapersShimmer.setVisibility(View.GONE);
                binding.WallpapersShimmer.stopShimmer();
            }else {
                Log.e("TAG", "onPostExecute: "+"Fragment not attached" );
            }

        }
    }
}