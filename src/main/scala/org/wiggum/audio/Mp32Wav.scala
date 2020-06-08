package org.wiggum.audio

import java.io.{File, FileInputStream, InputStream}
import java.util.UUID

import javazoom.jl.converter.Converter
import org.pmw.tinylog.Logger

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.global

/**
 *
 * Created on 6/7/20.
 */
trait Mp32Wav {
  import Mp32Wav._

  /**
   * Convert an mpeg stream to a wav (pcm) stream.
   *
   * TODO super hacky
   *
   * @param mpeg
   * @return
   */
  def convert(mpeg: InputStream): InputStream = {
    val destName: String = s"target/converted-${UUID.randomUUID().toString}.wav"
    val f: File = new File(destName)
    f.createNewFile()
    val converter: Converter = new Converter()

    Future {
      Logger.info("starting audio converter")
      converter.convert(mpeg, destName, null, null)
    }

    Thread.sleep(1_000)
    new FileInputStream(f)
  }
}

object Mp32Wav {
  implicit val ec: ExecutionContext = global
}