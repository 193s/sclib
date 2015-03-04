
import java.io.File
import java.io.PrintWriter
import java.io.FileOutputStream
import java.io.ByteArrayOutputStream
import java.io.BufferedReader

package object sclib {
  /** THEN */
  implicit class Then[S](o: S) {
    def $[T](x: S => T) = x(o)
  }

  /** Useful sets of characters */
  object Chars {
    val digits          = ('0' to '9').toSeq
    val lower_alphabets = ('a' to 'z').toSeq
    val upper_alphabets = ('A' to 'Z').toSeq
    val alphabets       = lower_alphabets ++ upper_alphabets
    val alphaOrDigits   = digits ++ alphabets
    val printableChars  = (32 to 126).map(_.toChar).toSeq
  }


  /** prints execution time(ms) of `proc` */
  def printExecTime(proc: => Unit) = {
    val start = System.currentTimeMillis
    proc
    println((System.currentTimeMillis - start) + " ms")
  }



  import java.security.{MessageDigest => MD}
  def hash(algorithm: String, str: String) =
    MD.getInstance(algorithm)
      .digest(str.getBytes)
      .map("%02x" format _)
      .mkString

  // == ENCRYPTION ==
  import java.util.Base64
  private lazy val base64encoder      = Base64.getEncoder
  private lazy val base64encoderNoPad = base64encoder.withoutPadding
  private lazy val base64decoder      = Base64.getDecoder
  /** base64 encode */
  def base64encode(str: String, padding: Boolean = true) = {
    val encoder: Base64.Encoder = if (padding) base64encoder else base64encoderNoPad
    new String(encoder.encode(str.getBytes))
  }
  /** base64 decode */
  def base64decode(str: String) = new String(base64decoder.decode(str))


  // == EXTENSION ==
  /** Rich String */
  implicit class RichStr(val s: String) {
    /** n-ary string to BigInt */
    def base(n: Int) = BigInt(s, n)
    /** binary string to BigInt */
    def bin = base(2)
    /** octal string to BigInt */
    def oct = base(8)
    /** hex string to BigInt */
    def hex = base(16)
    /** decimal string to BigInt */
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
    /** print to `file` with EOL */
    def print(file: File) = printToFile(file)(_.println(s))
  }


  /** shortcut for new java.io.File(string) */
  object File {
    def apply(file: String) = new File(file)
  }

  /** print to `file` */
  def printToFile(file: String)(op: PrintWriter => Unit): Unit =
    printToFile(new File(file))(op)

  /** print to `file` */
  def printToFile(file: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(file)
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


  // == PROCESS ==

  import scala.language.postfixOps
  import scala.sys.process.Process
  /** execute command (sync) */
  def system(cmd: String): String = Process(cmd)!!

  /** execute command (async) */
  def run(cmd: String): Process = Process(cmd) run



  /** splittable list */
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
