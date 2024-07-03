package com.hlopezg.presentation.content.injection

import com.google.ai.client.generativeai.GenerativeModel
import com.hlopezg.presentation_common_content_detail.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenerativeModule {
    @Provides
    fun provideGenerativeModule(
    ): GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEN_IA_KEY
    )
}