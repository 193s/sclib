# sclib
A tiny scala library for CTFers

Scaladoc API: [193s.github.io/sclib/doc/1.1.0/](193s.github.io/sclib/doc/1.1.0/)

---------------------

## Install

```sh
$ git clone https://github.com/193s/sclib
$ cd sclib
```

```sh
$ sbt package
```
=> **target/scala-\*/sclib_*-1.1.jar**


## example

#### Hex
```scala
import sclib._

"deadbeef".hex // => 3735928559: BigInt
```


#### Crypto/Hash
```scala
import sclib._

"aaaaa".base64enc  // => "YWFhYWE="
"QUJDREVGRw==".base64dec  // => "ABCDEFG"

"aaaaa".md5  // => 594f803b380a41396ed63dca39503542
"abc".hash("sha-256")  // => -4587e94070fe3015bebebf21a251dddc4ffc9e5c69e885634bef009e0dffea53
```

#### Web
```scala
import sclib.Web._

for (line <- urlopen("http://example.com")) println(line)
// => <!doctype html>
//    <html>...

urlencode("?a=100")  // => "%3Fa%3D100"
```

