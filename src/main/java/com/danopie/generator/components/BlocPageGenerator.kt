package com.danopie.generator.components

import com.danopie.generator.base.Generator


class BlocPageGenerator(
    blocName: String,
    blocShouldUseEquatable: Boolean
) : Generator(blocName, blocShouldUseEquatable, templateName = "screen") {

    override fun fileName() = "${snakeCase()}_screen.${fileExtension()}"
}