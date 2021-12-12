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
//
public class CoatOfArmsFragment extends Fragment {
// Фабричный метод создания фрагмента
// Фрагменты рекомендуется создавать через фабричные методы.

    public static CoatOfArmsFragment create (int index){
        CoatOfArmsFragment f = new CoatOfArmsFragment(); // создание фрагмента
        // передача параметра
        Bundle args = new Bundle();
        args.putInt("index",index);
        f.setArguments(args);
        return f;
    }
    //Получить индекс из списка(фактически из параметра)
    public int getIndex(){
        int index = getArguments().getInt("index",0);
        return index;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Определить какой герб надо показать, и показать его
        ImageView coatOfArms = new ImageView(getContext());
        // Получить из ресурсов массив указателей на изображения гербов
        TypedArray imgs = getResources().obtainTypedArray(R.array.coatfarms_imgs);
        //Выбрать по индексу подходящий
        coatOfArms.setImageResource(imgs.getResourceId(getIndex(),-1));

       // Вместо макета используем сразу картинку
        return coatOfArms;
    }
}