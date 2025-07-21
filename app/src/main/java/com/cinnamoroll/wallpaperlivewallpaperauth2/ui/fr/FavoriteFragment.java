package com.cinnamoroll.wallpaperlivewallpaperauth2.ui.fr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;


import com.cinnamoroll.wallpaperlivewallpaperauth2.adapters.FavAdapter;
import com.cinnamoroll.wallpaperlivewallpaperauth2.config.RoomDatabase.MyDataBse;
import com.cinnamoroll.wallpaperlivewallpaperauth2.config.RoomDatabase.MyFavs;
import com.cinnamoroll.wallpaperlivewallpaperauth2.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
FragmentFavoritesBinding binding;
List<MyFavs> myFavs = new ArrayList<>();
FavAdapter favAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding=FragmentFavoritesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MyDataBse myDataBse = MyDataBse.getInstance(getContext());
        myFavs = myDataBse.favDao().getAllFavs();
        favAdapter = new FavAdapter(getActivity(),myFavs);
        binding.ff.setText(myFavs.size() +" wallpapers");
        if (myFavs.isEmpty()){
            binding.FavRecycler.setVisibility(View.GONE);
            binding.NoFav.setVisibility(View.VISIBLE);
        }else {
            binding.FavRecycler.setVisibility(View.VISIBLE);
            binding.NoFav.setVisibility(View.GONE);
            binding.FavRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
            binding.FavRecycler.setAdapter(favAdapter);
        }



    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        if (favAdapter!=null){
            MyDataBse myDataBse = MyDataBse.getInstance(getContext());
            List<MyFavs> myFavs = myDataBse.favDao().getAllFavs();
            favAdapter = new FavAdapter(getActivity(),myFavs);
            binding.FavRecycler.setAdapter(favAdapter);
            favAdapter.notifyDataSetChanged();
            binding.ff.setText(myFavs.size() +" wallpapers");
        }
    }
}