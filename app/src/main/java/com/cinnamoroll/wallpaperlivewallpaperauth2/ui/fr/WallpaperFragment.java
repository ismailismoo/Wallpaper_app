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


import com.cinnamoroll.wallpaperlivewallpaperauth2.MyUtils;
import com.cinnamoroll.wallpaperlivewallpaperauth2.adapters.FeaturedAdapter;
import com.cinnamoroll.wallpaperlivewallpaperauth2.databinding.FragmentHomeBinding;
import com.cinnamoroll.wallpaperlivewallpaperauth2.models.FeaturedModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WallpaperFragment extends Fragment {

    FragmentHomeBinding binding;
    List<FeaturedModel> featuredModels = new ArrayList<>();
    FeaturedAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            if (MyUtils.appControl.isShowMockupData()){
                binding.load1.setVisibility(View.VISIBLE);
                binding.load2.setVisibility(View.VISIBLE);
                binding.featuredRecycler.setVisibility(View.GONE);
                binding.WallpapersShimmer.stopShimmer();
                binding.WallpapersShimmer.setVisibility(View.GONE);
                binding.SampleWallpapers.setVisibility(View.VISIBLE);
                List<FeaturedModel> featuredModels1 = new ArrayList<>();
                featuredModels1.add(new FeaturedModel("https://epicappquest.com/samplewallpapers/wall1.jpeg", false));
                featuredModels1.add(new FeaturedModel("https://epicappquest.com/samplewallpapers/wall2.jpg", false));
                featuredModels1.add(new FeaturedModel("https://epicappquest.com/samplewallpapers/wall3.jpeg", false));
                featuredModels1.add(new FeaturedModel("https://epicappquest.com/samplewallpapers/wall4.jpeg", false));
                featuredModels1.add(new FeaturedModel("https://epicappquest.com/samplewallpapers/wall5.jpeg", false));
                featuredModels1.add(new FeaturedModel("https://epicappquest.com/samplewallpapers/wall6.jpeg", false));
                binding.SampleWallpapers.setLayoutManager(new GridLayoutManager(getContext(), 3));
                FeaturedAdapter adapter1 = new FeaturedAdapter(getActivity(), featuredModels1);
                binding.SampleWallpapers.setAdapter(adapter1);

            }else {
                    GetWallpaperCaveData getWallpaperCaveData = new GetWallpaperCaveData();
                    getWallpaperCaveData.execute();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class GetWallpaperCaveData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(MyUtils.NicheLink).userAgent("firefox").followRedirects(false).get();
                Elements elements = doc.select("div#albumwp").select("div.wallpaper");

                for (Element element : elements) {
                    String img = element.select("img.wimg").attr("src");
                    if (elements.indexOf(element) > 5) {
                        featuredModels.add(new FeaturedModel("https://wallpapercave.com" + img, true));
                    } else {
                        featuredModels.add(new FeaturedModel("https://wallpapercave.com" + img, false));
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
                adapter = new FeaturedAdapter(getActivity(), featuredModels);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int viewType = Objects.requireNonNull(binding.featuredRecycler.getAdapter()).getItemViewType(position);
                        return viewType == FeaturedAdapter.AD_VIEW ? 3 : 1;
                    }
                });
                binding.featuredRecycler.setLayoutManager(gridLayoutManager);
                binding.featuredRecycler.setAdapter(adapter);
                binding.featuredRecycler.setHasFixedSize(true);
                binding.WallpapersShimmer.stopShimmer();
                binding.WallpapersShimmer.setVisibility(View.GONE);
            } else {
                Log.e("TAG", "onPostExecute: " + "Fragment not attached");
            }

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}