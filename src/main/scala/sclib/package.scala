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

  private val base64encoder = Base64.getEncoder
  private val base64decoder = Base64.getDecoder
  /** base64 encode */
  def base64encode(str: String) = new String(base64encoder.encode(str.getBytes))
  /** base64 encode (no padding) */
  def base64noPadding(str: String) = new String(base64encoder.withoutPadding.encode(str.getBytes))
  /** base64 decode */
  def base64decode(str: String) = new String(base64decoder.decode(str))



  /** rich string */
  implicit class RichStr(val s: String) {
    def base(b: Int) = BigInt(s, b)
    def bin = base(2)
    def oct = base(8)
    def hex = base(16)
    def value = base(10)
    def base64enc = base64encode(s)
    def base64dec = base64decode(s)
    def md5 = sclib.hash(Hash.MD5, s)
    def sha256 = sclib.hash(Hash.SHA256, s)
    def sha512 = sclib.hash(Hash.SHA512, s)
    def hash(algorithm: String) = sclib.hash(algorithm, s)
  }


  implicit class SplittableList[T](list: List[T]) {
    def splitBy(f: T => Boolean): List[List[T]] = splitList(list, f)

    private def splitList(xs: List[T], p: T => Boolean): List[List[T]] = {
      val (a, b) = xs.span((a: T) => !p(a))
      b.isEmpty match {
        case true  => List[List[T]](a)
        case false => List[List[T]](a) ++ splitList(b.tail, p)
      }
    }
  }
}
