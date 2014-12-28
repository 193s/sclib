package sclib

import java.io.{InputStreamReader, BufferedReader, InputStream, FileOutputStream}

import scala.io.Codec._
import scala.io.Source


package object io {
  object BinWriter {
    def apply(filename: String) = new FileOutputStream(filename)
  }
  object BinReader {
    def apply(filename: String) = Source.fromFile(filename)(ISO8859)
  }
  object BufReader {
    def apply(in: InputStream) = new BufferedReader(new InputStreamReader(in))
    def apply(in: InputStreamReader) = new BufferedReader(in)
  }
}

