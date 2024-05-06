# WordApp makefile
# Tamsanqa Thwala
# 05 May 2024

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
$(BINDIR)/%.class:$(SRCDIR)/%.java $<
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
CLASSES=WordDictionary.class Score.class WordRecord.class Dependencies.class WordPanel.class WordApp.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
default: $(CLASS_FILES)
clean:
	rm $(BINDIR)/*.class
run: $(CLASS_FILES)
	java -cp bin WordApp
