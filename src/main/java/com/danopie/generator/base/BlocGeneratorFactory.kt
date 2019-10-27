package com.danopie.generator.base

import com.danopie.generator.components.BlocStateGenerator
import com.danopie.generator.components.BlocGenerator
import com.danopie.generator.components.BlocPageGenerator

object BlocGeneratorFactory {

    fun getBlocGenerators(blocName: String, blocShouldUseEquatable: Boolean): List<Generator> {
        val bloc = BlocGenerator(blocName, blocShouldUseEquatable)
        val event = BlocStateGenerator(blocName, blocShouldUseEquatable)
        val state = BlocPageGenerator(blocName, blocShouldUseEquatable)
        return listOf(bloc, event, state)
    }
}