#!/usr/bin/env groovy

import groovy.transform.Canonical

@Canonical
class Color {
    String xterm
    String name
    String hex
    String rgb
    String hsl

    String toPrettyString(int c1, int c2, int c3, int c4) {
        xterm.padLeft(c1) + '  ' +
        name.padRight(c2) + '  ' +
        hex.padRight(c3)  + '  ' +
        rgb.padRight(c4)  + '  ' +
        hsl
    }
}

cs = new File('colors.csv').readLines().collect { line ->
    def (a, b, c, d, e) = line.tokenize('\t')
    new Color(a, b, c, d, e)
}

c1 = cs.max { it.xterm.size() } .xterm.size()
c2 = cs.max { it.name.size() } .name.size()
c3 = cs.max { it.hex.size() } .hex.size()
c4 = cs.max { it.rgb.size() } .rgb.size()

cs.each { println it.toPrettyString(c1, c2, c3, c4) }
