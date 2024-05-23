package com.hlopezg.androidacceleratormoviedb

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withModifier
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class KonsistTest {
    @Test
    fun `classes with 'UseCase' suffix should reside in 'usecase' package`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue { it.resideInPackage("..usecase..") }
    }

    @Test
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist.scopeFromProject()
            .classes()
            .withAllParentsOf(ViewModel::class)
            .assertTrue { it.name.endsWith("ViewModel") }
    }

    @Test
    fun `clean architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                // Define layers
                val domain = Layer("Domain", "com.hlopezg.domain..")
                val presentation = Layer("Presentation", "com.hlopezg.presentation..")
                val data_local = Layer("DataLocal", "com.hlopezg.data_local..")
                val data_remote = Layer("DataRemote", "com.hlopezg.data_remote..")
                val data_repository = Layer("DataRepository", "com.hlopezg.data_repository..")

                // Define architecture assertions
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data_local.dependsOn(data_repository, domain)
                data_remote.dependsOn(data_repository, domain)
                data_repository.dependsOn(domain)
            }
    }

    @Test
    fun `data classes has empty init block`() {
        Konsist.scopeFromProduction()
            .classes(includeNested = true)
            .withModifier(KoModifier.DATA)
            .assertTrue {
                it.initBlocks.isEmpty()
            }
    }
}