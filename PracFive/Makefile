JAVAC = javac
JFLAGS = -g
SOURCES = UserInterface.java \
	EfficiencyCheck.java \
	PracFiveSource/ChainedWord.java \
	PracFiveSource/Definition.java \
	PracFiveSource/FileUtil.java \
	PracFiveSource/LPHashtable.java \
	PracFiveSource/QPHashtable.java \
	PracFiveSource/SCHashtable.java \
	PracFiveSource/Word.java \
	PracFiveSource/WordType.java \
	PracFiveSource/ChainedEntry.java \
	PracFiveSource/Entry.java \
	PracFiveSource/Dictionary.java   

.SUFFIXES: .java .class

CLS= $(SOURCES:.java=.class)

all:	$(CLS)

.java.class:
	$(JAVAC) $(JFLAGS) $<


clean:
	@rm -f UserInterface*.class
	@rm -f $(SOURCES:.java=.class)
	

