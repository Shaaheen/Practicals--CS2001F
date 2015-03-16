JAVAC = javac
JFLAGS = -g
SOURCES =  \
	AVLTree.java \
	TreeUI.java \
	TreeUtils.java \
	AVLTreeNode.java \
	SimpleTreeWriter.java \
	SimpleTreeWriterImpl.java

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) $<

CLS= $(SOURCES:.java=.class)

all:	$(CLS)

clean:
	@rm -f $(SOURCES:.java=.class)

