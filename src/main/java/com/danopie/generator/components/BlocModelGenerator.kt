package com.danopie.generator.components

import com.danopie.generator.base.Generator


class BlocModelGenerator(
    blocName: String,
    blocShouldUseEquatable: Boolean
) : Generator(blocName, blocShouldUseEquatable, templateName = "model") {

    override fun fileName() = "${snakeCase()}_model.${fileExtension()}"
}