package org.wiggum.output

import com.amazonaws.transcribestreaming.retryclient.StreamTranscriptionBehaviorImpl
import org.wiggum.Conf._
import software.amazon.awssdk.services.transcribestreaming.model.{Result, TranscriptEvent, TranscriptResultStream}

import scala.jdk.CollectionConverters._

/**
 * Tweets in response to stream.
 *
 * Check stream for partial, and possibly
 *
 * Created on 6/7/20.
 */
class StreamTranscriptionTweeter extends StreamTranscriptionBehaviorImpl {
  val twitter: Twitter = new Twitter

  override def onStream(e: TranscriptResultStream): Unit = {
    val results: List[Result] = e.asInstanceOf[TranscriptEvent].transcript().results().asScala.toList
    val maybeTweet: Option[String] = buildTweet(results)
    maybeTweet.foreach(twitter.tweet(_, hashtags))
  }


  /**
   * Inspects transcription results to
   * @param results
   * @return
   */
  def buildTweet(results: List[Result]): Option[String] = {
    if (results.isEmpty) None
    else {

      Option(" hi ")
    }
  }
}
