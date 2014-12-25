import java.security.{MessageDigest => MD}
import java.util.Base64

package object sclib {
  /** Prints execution time of proc */
  def printExecTime(proc: => Unit) = {
    val start = System.currentTimeMillis
    proc
    println((System.currentTimeMillis - start) + " ms")
  }


  def hash(algorithm: String, str: String) =
    BigInt (
      MD.getInstance(algorithm)
        .digest(str.getBytes)
    ).toString(16)

  /** base64 encode */
  def base64(str: String) = new String(Base64.getEncoder.encode(str.getBytes))
  /** base64 encode without padding */
  def base64noPadding(str: String) = new String(Base64.getEncoder.withoutPadding.encode(str.getBytes))
  /** base64 decode */
  def base64decode(str: String) = new String(Base64.getDecoder.decode(str))


  /** hex string (int) **/
  class HexInt private(s: String) {
    def hex = Integer.parseInt(s, 16)
  }
  object HexInt {
    def apply(s: String) = new HexInt(s)
  }

  /** hex string (BigInt) **/
  class BigHex private(s: String) {
    def hex = BigInt(s, 16)
  }
  object BigHex {
    def apply(s: String) = new BigHex(s)
  }

  implicit def str2hexInt(str: String) = HexInt(str)


  /** convert str('base#num') to decimal */
  def base(str: String) = {
    val base = str.takeWhile(_.isDigit).toInt
    require(2 <= base && base <= 36 && str.substring(base.toString.length).startsWith("#"))
    BigInt(str.substring(base.toString.length + 1), base)
  }
}
