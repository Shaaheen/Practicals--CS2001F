JAVAC = javac
JFLAGS = -g
SOURCES = BinaryTreeNode.java BSTHarness.java MainClass.java SimpleBST.java SimpleTreeWriter.java SimpleTreeWriterImpl.java TreeUtils.java
.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) $<

MainClass.class: BinaryTreeNode.class BSTHarness.class MainClass.java SimpleBST.class SimpleTreeWriter.class SimpleTreeWriterImpl.class TreeUtils.class

clean:
	@rm -f $(SOURCES:.java=.class)

