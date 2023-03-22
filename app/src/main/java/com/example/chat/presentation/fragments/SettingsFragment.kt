package com.example.chat.presentation.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.chat.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}