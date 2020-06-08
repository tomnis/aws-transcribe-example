package org.wiggum

import java.io.InputStream
import java.net.URL
import java.util.concurrent.CompletableFuture

import com.amazonaws.transcribestreaming.AudioStreamPublisher
import com.amazonaws.transcribestreaming.retryclient.{StreamTranscriptionBehavior, TranscribeStreamingRetryClient}
import org.pmw.tinylog.Logger
import org.wiggum.Conf._
import org.wiggum.audio.Mp32Wav
import org.wiggum.output.StreamTranscriptionStdOut
import software.amazon.awssdk.services.transcribestreaming.model.{LanguageCode, MediaEncoding, StartStreamTranscriptionRequest}

trait WiggumLike extends Mp32Wav {

  /**
   * Builds a request for starting a transcribe session.
   *
   * @param mediaSampleRateHertz
   * @return
   */
  def buildStartReq(mediaSampleRateHertz: Int): StartStreamTranscriptionRequest = {
    StartStreamTranscriptionRequest.builder()
      .languageCode(LanguageCode.EN_US.toString)
      .mediaEncoding(MediaEncoding.PCM)
      .mediaSampleRateHertz(mediaSampleRateHertz)
      .build()
  }


  /**
   * Processes a stream, blocks until stream is closed
   */
  def processStream(): Unit = {
    Logger.info("wiggum starting!")

    val client: TranscribeStreamingRetryClient = new TranscribeStreamingRetryClient(awsCreds, awsTranscribeEndpoint, awsRegion)
    val url: URL = new URL("https://broadcastify.cdnstream1.com/29351")

    val in: InputStream = url.openStream()
    val wavIn: InputStream = this.convert(in)
    val mediaSampleRateHertz: Int = 24_000 //22050 //24_000 //44_100
    // TODO we should use something like the below to determine sample rate, but results in a stream reset error
    // AudioSystem.getAudioInputStream(wavIn).getFormat.getSampleRate.toInt
    val src: AudioStreamPublisher = new AudioStreamPublisher(wavIn)
    val handler: StreamTranscriptionBehavior = new StreamTranscriptionStdOut

    Logger.info("starting aws transcribe session")
    // at some point this will be completed (it seems eventually an EOF gets written), we should block and then close resources
    val f: CompletableFuture[Void] = client.startStreamTranscription(buildStartReq(mediaSampleRateHertz), src, handler)

    while (!f.isDone) {
      Thread.sleep(1_000)
    }

    Logger.info(s"closing")
    in.close()
    wavIn.close()
    Thread.sleep(1000)
    client.close()
    Thread.sleep(1000)
  }
}

/**
 *
 * Created on 6/7/20.
 */
object Wiggum extends App with WiggumLike {

  while (true) {
    processStream()
  }
}
