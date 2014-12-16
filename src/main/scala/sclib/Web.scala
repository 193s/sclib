package sclib

import java.net.URLEncoder

import sun.net.idn.Punycode

import scala.io.Source


object Web {
  def urlopen(url: String) = Source.fromURL(url).getLines()
  def urlencode(str: String) = URLEncoder.encode(str, "UTF-8")
  def punycode(str: String) = Punycode.encode(new StringBuffer(str), null).toString
  def punycodeDecode(str: String) = Punycode.decode(new StringBuffer(str), null).toString
}
