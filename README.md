Konami-CLJS
===========

[ ![Codeship Status for ericqweinstein/konami-cljs](https://codeship.com/projects/2c5b4cf0-45fd-0133-40e4-569fce9c4062/status?branch=master)](https://codeship.com/projects/104845)

## About
Konami-CLJS is a little library that enables the [Konami code](https://en.wikipedia.org/wiki/Konami_Code) on your site. It's more or less a port of [Konami-JS](https://github.com/snaptortoise/konami-js) to ClojureScript. Unlike the original, it does not (yet) support mobile touch events.

## Installation
```clojure
[org.clojars.eqw/konami-cljs "0.1.0-SNAPSHOT"]
```

## Requirements
* Clojure 1.7+
* ClojureScript 1.7.122+

## Examples
```clojure
(ns example.core
 (:require [konami-cljs :refer [konami]]))

;; Like Konami-JS, the function takes a string...
(konami "http://www.example.com")

;; ...or a callback to execute.
(konami #(js/alert "INFINITE LIVES"))
```

## Contributing
1. Branch (`λ git checkout -b fancy-new-feature`)
2. Commit (`λ git commit -m "Fanciness!"`)
3. Lint && test (`λ lein run-tests`)
4. Push (`λ git push origin fancy-new-feature`)
5. Ye Olde Pulle Request

## License
Copyright © 2015 Eric Weinstein.
Distributed under the MIT license (see LICENSE).
