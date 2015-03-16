JAVAC = javac
JFLAGS = -g
SOURCES = TreeUI.java \
	PracTwoSource/AVLTree.java \
	PracTwoSource/TreeUtils.java \
	PracTwoSource/AVLTreeNode.java \
	PracTwoSource/SimpleTreeWriter.java \
	PracTwoSource/SimpleTreeWriterImpl.java

.SUFFIXES: .java .class

CLS= $(SOURCES:.java=.class)

all:	$(CLS)

.java.class:
	$(JAVAC) $(JFLAGS) $<


clean:
	@rm -f TreeUI*.class
	@rm -f $(SOURCES:.java=.class)
	

