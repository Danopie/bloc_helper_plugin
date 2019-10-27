package com.danopie.generator.components

import com.danopie.generator.base.Generator


class BlocStateGenerator(
    blocName: String,
    blocShouldUseEquatable: Boolean
) : Generator(blocName, blocShouldUseEquatable, templateName = "state") {

    override fun fileName() = "${snakeCase()}_state.${fileExtension()}"
}