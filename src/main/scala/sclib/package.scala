
import java.io.File
import java.io.PrintWriter
import java.io.FileOutputStream
import java.io.ByteArrayOutputStream
import java.io.BufferedReader
import java.util.Base64
import java.security.{MessageDigest => MD}

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

  private lazy val base64encoder = Base64.getEncoder
  private lazy val base64decoder = Base64.getDecoder
  /** base64 encode */
  def base64encode(str: String) = new String(base64encoder.encode(str.getBytes))
  /** base64 encode (no padding) */
  def base64noPadding(str: String) = new String(base64encoder.withoutPadding.encode(str.getBytes))
  /** base64 decode */
  def base64decode(str: String) = new String(base64decoder.decode(str))



  /** rich string */
  implicit class RichStr(val s: String) {
    /** n-ary string to int */
    def base(n: Int) = BigInt(s, n)
    /** binary string to int */
    def bin = base(2)
    /** octal string to int */
    def oct = base(8)
    /** hex string to int */
    def hex = base(16)
    /** decimal string to int */
    def value = base(10)

    /** base64 encode */
    def base64enc = base64encode(s)
    /** base64 decode */
    def base64dec = base64decode(s)

    /** hash */
    def hash(algorithm: String) = sclib.hash(algorithm, s)
    def md5 = sclib.hash(Hash.MD5, s)
    def sha256 = sclib.hash(Hash.SHA256, s)
    def sha512 = sclib.hash(Hash.SHA512, s)


    /** print with EOL */
    def print() = println(s)
    /** print to `writer` with EOL */
    def print(writer: PrintWriter) = writer.println(s)
    /** print to `file` */
    def print(file: File) = new PrintWriter(file)
  }

  def printToFile(f: String)(op: PrintWriter => Unit): Unit =
    printToFile(new File(f))(op)

  def printToFile(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try op(p) finally p.close()
  }

  /** print byte array to `file` */
  def printByteArray(file: String)(arr: Array[Byte]) {
    val p = new ByteArrayOutputStream()
    p.write(arr, 0, arr.length)
    p.writeTo(new FileOutputStream(file))
  }


  /** read characters from `in` n times */
  def readChars(in: BufferedReader)(n: Int) = {
    val buf = new Array[Char](n)
    in.read(buf, 0, n)
    new String(buf)
  }

  // def system(str: String): String = Process(str)!!


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
