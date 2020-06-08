package org.wiggum.output

import com.amazonaws.transcribestreaming.retryclient.{StreamTranscriptionBehavior}
import org.pmw.tinylog.Logger
import software.amazon.awssdk.services.transcribestreaming.model.{Result, StartStreamTranscriptionResponse, TranscriptEvent, TranscriptResultStream}

import scala.jdk.CollectionConverters._

/**
 *
 * Created by tdm on 6/7/20.
 */
class StreamTranscriptionStdOut extends StreamTranscriptionBehavior {
  override def onError(e: Throwable): Unit = throw e

  override def onStream(e: TranscriptResultStream): Unit = {
    val results: List[Result] = e.asInstanceOf[TranscriptEvent].transcript().results().asScala.toList

    results.filterNot(_.isPartial).foreach { r =>
      Logger.info(r.alternatives.asScala.head.transcript)
    }
  }

  override def onResponse(r: StartStreamTranscriptionResponse): Unit = {
    Logger.info(s"=== Received Initial response. Request Id: ${r.requestId} ===")
  }

  override def onComplete(): Unit = {
    Logger.info("=== All records stream successfully ===")
  }
}
