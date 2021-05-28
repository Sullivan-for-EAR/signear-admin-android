package com.sullivan.signearadmin.ui_reservation.di

import com.sullivan.common.ui_common.navigator.ReservationNavigator
import com.sullivan.signearadmin.ui_reservation.navigator.ReservationNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun provideLoginNavigator(
        navigator: ReservationNavigatorImpl
    ): ReservationNavigator
}