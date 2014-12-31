# sclib
A tiny scala library for CTFers

---------------------

## Install

```sh
git clone https://github.com/193s/sclib
cd sclib
```

```sh
sbt package
```
=> **target/scala-\*/sclib_\*-\*.jar**

```sh
sbt doc
```
=> **target/scala-\*/api/index.html**


## example

#### Hex
```scala
import sclib._

"deadbeef".hex // => 3735928559: BigInt
```


#### Crypto/Hash
```scala
import sclib._

base64encode("aaaaa")  // => "YWFhYWE="
base64decode("QUJDREVGRw==")  // => "ABCDEFG"

md5("aaaaa")  // => 594f803b380a41396ed63dca39503542
hash("sha-256", "abc")  // => -4587e94070fe3015bebebf21a251dddc4ffc9e5c69e885634bef009e0dffea53
```

#### Web
```scala
import sclib.Web._

for (line <- urlopen("http://example.com")) println(line)
// => <!doctype html>
//    <html>...

urlencode("?a=100")  // => "%3Fa%3D100"
```
