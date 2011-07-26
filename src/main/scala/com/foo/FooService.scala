package com.foo

import blueeyes.{BlueEyesServer, BlueEyesServiceBuilder}
import blueeyes.concurrent.Future
import blueeyes.core.http.combinators.HttpRequestCombinators
import blueeyes.core.http.MimeTypes._
import blueeyes.core.http.{HttpRequest, HttpResponse}
import blueeyes.core.service.ServerHealthMonitorService
import blueeyes.core.data.{BijectionsChunkJson, ByteChunk, BijectionsChunkString}

object FooServer extends BlueEyesServer with FooService with ServerHealthMonitorService {
  override def main(args: Array[String]) = super.main(Array("--configFile", "foo.conf"))
}

trait FooService extends BlueEyesServiceBuilder with HttpRequestCombinators
    with BijectionsChunkString with BijectionsChunkJson  {

  val fooService = service("foo", "0.1.0") {
    healthMonitor { monitor => context =>
      startup {
        FooConfig(context.config("foo")).future
      } ->
      request { config: FooConfig =>

        path("/") {
          produce(text/plain) {
            get {
              request: HttpRequest[ByteChunk] => Future.sync(HttpResponse[String](content = Some(config.foo)))
            }
          }
        }

      } ->
      shutdown { config: FooConfig =>
        ().future
      }
    }
  }
}


case class FooConfig(foo: String)



