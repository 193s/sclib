package sclib

import java.util.Base64
import scala.io.Source


object Tools {
  def urlopen(url: String) = Source.fromURL(url).getLines()

  def base64(str: String) = new String(Base64.getEncoder.encode(str.getBytes))

  def base64decode(str: String) = new String(Base64.getDecoder.decode(str))
}
