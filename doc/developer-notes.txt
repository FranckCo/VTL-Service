# Developer notes

## Web service to get an image of the parse tree

The `org.antlr.v4.gui.Trees` class contains methods that produce a PostScript (PS) file out of an ANTLR tree (see [Javadoc](https://www.antlr.org/api/JavaTool/org/antlr/v4/gui/Trees.html)).

*Attention:* the Antler runtime package also contains a `Trees` class (`org.antlr.v4.runtime.tree.Trees`), which does not contain the PS methods. The Maven [ANTLR 4 Runtime](https://mvnrepository.com/artifact/org.antlr/antlr4-runtime) dependency is not sufficient to use the `org.antlr.v4.gui.Trees` class: it has to be replaced by [ANTLR 4 Tool](https://mvnrepository.com/artifact/org.antlr/antlr4) (which requires the runtime).

The [Ghost4J library](http://www.ghost4j.org/) seems to be a solution for converting PS files to images.