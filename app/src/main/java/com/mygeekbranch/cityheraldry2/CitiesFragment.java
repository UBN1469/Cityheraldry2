package com.mygeekbranch.cityheraldry2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

// Фрагмент выбора города из списка
public class CitiesFragment extends Fragment {
    int currentPosition = 0; // Текущая позиция (выбранный город)
    boolean isExistCoatOfArms; // Можно ли расположить рядом фрагмент с гербом

    // При создании фрагмента укажем его макет
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        // activity создана, можно к ней обращаться. Выполним начальные действия
        // перенес сюда. т.к. onActivityCreated диприкейт
        isExistCoatOfArms = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null){
            // Восстановление текущей позиции.
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }
        // Если можно нарисовать рядом герб, то сделаем это
        if (isExistCoatOfArms) {
            showCoatOfArms();

        }



    }

    // activity создана, можно к ней обращаться. Выполним начальные действия


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
        // Определение, можно ли будет расположить рядом герб в другом фрагменте
//
//        isExistCoatOfArms = getResources().getConfiguration().orientation ==
//                Configuration.ORIENTATION_LANDSCAPE;
//        // Если это не первое создание, то восстановим текущую позицию
//        if (savedInstanceState != null){
//            // Восстановление текущей позиции.
//            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
//        }
//        // Если можно нарисовать рядом герб, то сделаем это
//        if (isExistCoatOfArms){
//            showCoatOfArms();
//        }
// }
    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity",currentPosition);
        super.onSaveInstanceState(outState);
    }

    // Создаём список городов на экранe из масссива в ресурсах
    private void initList(View view){
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);
        // В этом цикле создаем элемент TextView,
// заполняем его значениями,
// и добавляем на экран.
// Кроме того, создаем обработку касания на элемент
        for (int i = 0; i < cities.length ; i++) {
            String city =cities[i];
            TextView tv =new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition = fi;
                    showCoatOfArms();
                }
            });
        }

    }
    // Показать герб. Ecли возможно, то показать рядом со списком,
    // если нет, то открыть вторую activity
    
    private void showCoatOfArms(){
        if (isExistCoatOfArms){
            // Проверим, что фрагмент с гербом существует в активити
            CoatOfArmsFragment detail = (CoatOfArmsFragment)getFragmentManager().findFragmentById(R.id.coat_of_arms);
            // Если есть необходимость, то выведем герб
            if (detail == null || detail.getIndex() != currentPosition){
                // Создаем новый фрагмент с текущей позицией для вывода герба
                detail = CoatOfArmsFragment. create ( currentPosition );
                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail); // Замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Если нельзя вывести герб рядом, откроем вторую активити
            //Intent intent = new Intent();
            //intent.setClass(getActivity(), CoatOfArmsActivity.class);

            Intent intent = new Intent(getActivity(),CoatOfArmsActivity.class);

            // И передадим туда параметры
            intent.putExtra("index",currentPosition);
            startActivity(intent);
        }
    }


}