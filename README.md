# sclib
A tiny scala libary for CTFers

---------------------

## Install
```
git clone https://github.com/193s/sclib
cd sclib
```

```
sbt package
```
=> **target/scala-\*/sclib_\*-\*.jar**

```
sbt doc
```
=> **target/scala-\*/api/index.html**

## example

#### Hash
```
import sclib.Hash._

md5("aaaaa")
hash("md5", "abc")
```

#### Crypto
```
import sclib.Crypto._

base64("aaaaa")
base64decode("YWFhYWEK")
```

#### Web
```
import sclib.Web._

for (line <- urlopen("http://example.com")) println(line)

urlencode("?a=100")
```
