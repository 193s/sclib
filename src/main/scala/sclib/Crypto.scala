package sclib

import java.util.Base64


object Crypto {
  /** base64 encode */
  def base64(str: String) = new String(Base64.getEncoder.encode(str.getBytes))
  /** base64 encode without padding */
  def base64noPadding(str: String) = new String(Base64.getEncoder.withoutPadding.encode(str.getBytes))
  /** base64 decode */
  def base64decode(str: String) = new String(Base64.getDecoder.decode(str))
}
