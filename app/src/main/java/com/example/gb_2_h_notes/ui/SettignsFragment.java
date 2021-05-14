package com.example.gb_2_h_notes.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.gb_2_h_notes.EngineSettings;
import com.example.gb_2_h_notes.R;

public class SettignsFragment extends Fragment {

    public SettignsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settigns, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initSwitchBackStack(view);
        initRadioAdd(view);
        initRadioReplace(view);
        initSwitchBackAsRemove(view);
        initSwitchDeleteBeforeAdd(view);
    }

    private void initSwitchBackStack(View view) {
        // Элемент пользовательского интерфейса — переключатель
        // По функционалу очень похож на CheckBox, но имеет другой дизайн
        SwitchCompat switchUseBackStack = view.findViewById(R.id.switchBackStack);
        switchUseBackStack.setChecked(EngineSettings.isBackStack);
        switchUseBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EngineSettings.isBackStack = isChecked;
                writeSettings();
            }
        });
    }

    private void initRadioAdd(View view) {
        RadioButton radioButtonAdd = view.findViewById(R.id.radioButtonAdd);
        radioButtonAdd.setChecked(EngineSettings.isAddFragment);
        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EngineSettings.isAddFragment = isChecked;
                writeSettings();
            }
        });
    }

    private void initRadioReplace(View view) {
        RadioButton radioButtonReplace = view.findViewById(R.id.radioButtonReplace);
        radioButtonReplace.setChecked(!EngineSettings.isAddFragment);
        radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EngineSettings.isAddFragment = !isChecked;
                writeSettings();
            }
        });
    }

    private void initSwitchBackAsRemove(View view) {
        SwitchCompat switchBackAsRemove = view.findViewById(R.id.switchBackAsRemove);
        switchBackAsRemove.setChecked(EngineSettings.isBackAsRemove);
        switchBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EngineSettings.isBackAsRemove = isChecked;
                writeSettings();
            }
        });
    }

    private void initSwitchDeleteBeforeAdd(View view) {
        SwitchCompat switchDeleteBeforeAdd = view.findViewById(R.id.switchDeleteBeforeAdd);
        switchDeleteBeforeAdd.setChecked(EngineSettings.isDeleteBeforeAdd);
        switchDeleteBeforeAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EngineSettings.isDeleteBeforeAdd = isChecked;
                writeSettings();
            }
        });
    }

    // Сохранение настроек приложения
    private void writeSettings() {

        // Специальный класс для хранения настроек
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(EngineSettings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        // Настройки сохраняются посредством специального класса editor
        SharedPreferences.Editor editor = sharedPref.edit();

        // Задаём значения настроек
        editor.putBoolean(EngineSettings.IS_BACK_STACK_USED, EngineSettings.isBackStack);
        editor.putBoolean(EngineSettings.IS_ADD_FRAGMENT_USED, EngineSettings.isAddFragment);
        editor.putBoolean(EngineSettings.IS_BACK_AS_REMOVE_FRAGMENT, EngineSettings.isBackAsRemove);
        editor.putBoolean(EngineSettings.IS_DELETE_FRAGMENT_BEFORE_ADD, EngineSettings.isDeleteBeforeAdd);

        // Сохраняем значения настроек
        editor.apply();
    }
}