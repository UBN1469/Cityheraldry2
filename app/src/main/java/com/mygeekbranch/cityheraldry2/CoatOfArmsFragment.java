package com.mygeekbranch.cityheraldry2;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//
public class CoatOfArmsFragment extends Fragment {
// Фабричный метод создания фрагмента
// Фрагменты рекомендуется создавать через фабричные методы.
    public static final String PARCEL = "parsel";

    //public static CoatOfArmsFragment create (int index){
    public static CoatOfArmsFragment create (Parcel parcel){
        CoatOfArmsFragment f = new CoatOfArmsFragment(); // создание фрагмента
        // передача параметра
        Bundle args = new Bundle();
        //args.putInt("index",index);
        args.putSerializable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }
    //Получить индекс из списка(фактически из параметра)
//    public int getIndex(){
//        int index = getArguments().getInt("index",0);
//        return index;
//    }

    // Получить посылку из параметров:
    public Parcel getParsel(){
        Parcel parcel = (Parcel) getArguments().getSerializable(PARCEL);
        return parcel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Определить какой герб надо показать, и показать его
        //ImageView coatOfArms = new ImageView(getContext());
        View layout = inflater.inflate(R.layout.fragment_coat_of_arms,container,false);
        ImageView coatOfArms = layout.findViewById(R.id.imageView);
        TextView cityNameView = layout.findViewById(R.id.textView);
        // Получить из ресурсов массив указателей на изображения гербов
        TypedArray imgs = getResources().obtainTypedArray(R.array.coatfarms_imgs);
        Parcel parcel = getParsel();
        //Выбрать по индексу подходящий
        //coatOfArms.setImageResource(imgs.getResourceId(getIndex(),-1));
        coatOfArms.setImageResource(imgs.getResourceId(parcel.getImageIndex(),-1));
        cityNameView.setText(parcel.getCitiName());


       // Вместо макета используем сразу картинку
       // return coatOfArms;

        return layout;
    }
}