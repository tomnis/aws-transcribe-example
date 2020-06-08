package org.wiggum.output

import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.Tweet
import org.wiggum.Conf._

import scala.concurrent.Future

/**
 *
 * Created on 6/7/20.
 */
trait TwitterLike {

  lazy val client = new TwitterRestClient(twitConsumerToken, twitAccessToken)

  def tweet(status: String, hashtags: Seq[String] = Seq.empty): Future[Tweet] = {
    val suffix: String = if (hashtags.isEmpty) "" else ("\n\n" + hashtags.mkString(" "))
    client.createTweet(status + suffix)
  }
}

class Twitter extends TwitterLike
