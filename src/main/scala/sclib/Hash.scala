package sclib

import java.security.{MessageDigest => MD}


object Hash {
  val MD5 = "md5"
  val SHA256 = "sha-256"
  val SHA512 = "sha-512"

  def hash(algorithm: String, str: String) =
    BigInt (
      MD.getInstance(algorithm)
        .digest(str.getBytes)
    ).toString(16)

  def md5(str: String) = hash(MD5, str)
}
