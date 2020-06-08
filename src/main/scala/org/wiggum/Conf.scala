package org.wiggum

import com.danielasfregola.twitter4s.entities.{AccessToken, ConsumerToken}
import software.amazon.awssdk.auth.credentials.{AwsCredentialsProvider, DefaultCredentialsProvider}
import software.amazon.awssdk.regions.Region

/**
 *
 * Created by tdm on 6/7/20.
 */
object Conf {

  lazy val awsCreds: AwsCredentialsProvider = DefaultCredentialsProvider.create()
  lazy val awsRegion: Region = Region.US_WEST_2

  lazy val awsTranscribeEndpoint: String = "https://transcribestreaming." + awsRegion.toString.toLowerCase.replace('_', '-') + ".amazonaws.com"


  lazy val hashtags: Seq[String] = Seq("#pdxscanner", "#pdxscannerduty")

  lazy val twitConsumerToken: ConsumerToken = ???
  lazy val twitAccessToken: AccessToken = ???
}
