package sclib

import java.util.Base64


object Encryption {
  def base64(str: String) = new String(Base64.getEncoder.encode(str.getBytes))
  def base64decode(str: String) = new String(Base64.getDecoder.decode(str))
}
