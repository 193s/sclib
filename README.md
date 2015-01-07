# sclib
A tiny scala library for CTFers

Scaladoc API: [193s.github.io/sclib/doc/1.1.0/](https://193s.github.io/sclib/doc/1.1.0/)

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


## Example

```scala
"FLAG".md5.hex.toString.map(_.toInt.toBinaryString).toList.toString.base64enc.print
```
result:
```
TGlzdCgxMTAwMTAsIDExM......DEwLCAxMTAwMTAsIDExMDEwMCk=
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

