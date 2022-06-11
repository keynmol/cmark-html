resolvers += Resolver.sonatypeRepo("snapshots")
scalaVersion := "3.1.2"

name := "htmark"

enablePlugins(ScalaNativePlugin, BindgenPlugin, VcpkgPlugin)

import bindgen.interface.Binding

vcpkgDependencies := Set("cmark")
nativeConfig := {
  val conf = nativeConfig.value

  conf
    .withCompileOptions(
      conf.compileOptions ++ vcpkgCompilationArguments.value
    )
    .withLinkingOptions(
      conf.linkingOptions ++ vcpkgLinkingArguments.value
    )
}
bindgenBindings := Seq(
  Binding(
    vcpkgManager.value.includes("cmark") / "cmark.h",
    "cmark",
    cImports = List("cmark.h"),
    clangFlags = List("-I" + vcpkgManager.value.includes("cmark").toString)
  )
)

Global / onChangedBuildSource := ReloadOnSourceChanges
