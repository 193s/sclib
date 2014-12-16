

package object sclib {
  /** Prints execution time of proc */
  def printExecTime(proc: => Unit) = {
    val start = System.currentTimeMillis
    proc
    println((System.currentTimeMillis - start) + " ms")
  }

  /** hex string to int */
  def hex2int(hex: String): Int = Integer.parseInt(hex, 16)
  /** hex string to long */
  def hex2long(hex: String): Long = java.lang.Long.parseLong(hex, 16)


  /** hex string (int) **/
  class HexString private(s: String) {
    def hex = Integer.parseInt(s, 16)
  }
  object HexString {
    def apply(s: String) = new HexString(s)
  }

  /** hex string (BigInt) **/
  class BigHex private(s: String) {
    def hex = BigInt(s, 16)
  }
  object BigHex {
    def apply(s: String) = new BigHex(s)
  }

  implicit def str2hex(str: String) = HexString(str)
}
