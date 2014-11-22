package sclib

import java.net.URLEncoder

import scala.io.Source


object Network {
  def urlopen(url: String) = Source.fromURL(url).getLines()
  def urlencode(str: String) = URLEncoder.encode(str, "UTF-8")
}
