# htmark

Small CLI app in Scala Native that uses [cmark](https://github.com/commonmark/cmark) to convert a markdown file to HTML.

Usage: `htmark <filename.md>` - will spit out HTML to standard output.

It uses:

1. [sbt-vcpkg](https://github.com/indoorvivants/sbt-vcpkg) to install native dependencies using [Vcpkg](vcpkg.io)
2. [sn-bindgen](https://sn-bindgen.indoorvivants.com) to generate bindings to cmark library

The built binary is statically linked on both MacOS and Linux, and requires no cmark installed anywhere.

The Github actions build and upload the binaries.
