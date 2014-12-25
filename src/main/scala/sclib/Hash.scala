package sclib

object Hash {
  val MD5 = "md5"
  val SHA256 = "sha-256"
  val SHA512 = "sha-512"

  def md5(str: String) = hash(MD5, str)
}
