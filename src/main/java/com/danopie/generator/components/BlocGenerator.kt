package com.danopie.generator.components

import com.danopie.generator.base.Generator


class BlocGenerator(
    blocName: String,
    blocShouldUseEquatable: Boolean
) : Generator(blocName, blocShouldUseEquatable, templateName = "bloc") {

    override fun fileName() = "${snakeCase()}_bloc.${fileExtension()}"
}