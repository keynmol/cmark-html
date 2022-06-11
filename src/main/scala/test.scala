import cmark.functions.*
import cmark.types.*

import scala.scalanative.unsafe.*
import scalanative.libc.stdio.*
import scalanative.libc.*
import scala.util.Using
import scala.util.Using.Releasable

@main def hello(filename: String) =
  given Releasable[Ptr[FILE]] with
    def release(resource: Ptr[FILE]): Unit =
      fclose(resource)

  Zone { implicit z =>
    Using.resource(fopen(toCString(filename), c"r")) { ptr =>
      val node = cmark_parse_file(ptr, 0)
      val html = cmark_render_html(node, 0)
      cmark_node_free(node)
      stdio.printf(c"%s", html)
    }
  }
end hello
