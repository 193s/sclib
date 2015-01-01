package sclib

import java.io.InputStream
import java.net.URLEncoder
import java.net.Authenticator
import java.net.PasswordAuthentication
import sun.net.idn.Punycode
import scala.io.Source


object Web {
  def urlopen(url: String) = Source.fromURL(url).getLines()
  def urlencode(str: String) = URLEncoder.encode(str, "UTF-8")
  def punycode(str: String) = Punycode.encode(new StringBuffer(str), null).toString
  def punycodeDecode(str: String) = Punycode.decode(new StringBuffer(str), null).toString
  def readAll(in: InputStream) =
    Stream.continually(in.read()).takeWhile(-1 !=).map(_.toChar).mkString

  def setAuth(usr: String, passwd: String): Unit = Authenticator.setDefault (
    new Authenticator() {
      override protected def getPasswordAuthentication() =
        new PasswordAuthentication(usr, passwd.toCharArray)
    }
  )
}
