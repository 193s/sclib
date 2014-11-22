package sclib

import java.util.Base64


object Crypto {
  def base64(str: String) = new String(Base64.getEncoder.encode(str.getBytes))
  def base64noPadding(str: String) = new String(Base64.getEncoder.withoutPadding.encode(str.getBytes))
  def base64decode(str: String) = new String(Base64.getDecoder.decode(str))
}
