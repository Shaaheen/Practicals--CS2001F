JAVAC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES = \
	TreeUI.java \
	AVLTreeNode.java \
	AVLTree.java \
	TreeUtils.java \
	SimpleTreeWriter.java \
	SimpleTreeWriterImpl.java



default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

