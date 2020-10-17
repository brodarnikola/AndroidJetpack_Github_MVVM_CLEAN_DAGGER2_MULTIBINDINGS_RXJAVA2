package com.vjezba.androidjetpackgithub.di


import com.vjezba.androidjetpackgithub.ui.fragments.EnterDetailsFragment
import com.vjezba.androidjetpackgithub.ui.fragments.LegoSetFragment
import com.vjezba.androidjetpackgithub.ui.fragments.LegoSetsFragment
import com.vjezba.androidjetpackgithub.ui.fragments.LegoThemeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): LegoThemeFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetsFragment(): LegoSetsFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetFragment(): LegoSetFragment


    @ContributesAndroidInjector
    abstract fun contributeEnterDetailsFragment(): EnterDetailsFragment
}
