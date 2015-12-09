LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler

group:
	@echo "Archit Aggarwal ECN account - aggarw13"

compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java

test1: compiler
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test1.micro > outputs/test1.out
	diff -B -b exp_outputs/sqrt.out outputs/sqrt.out

test2: 
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/$(TESTARGS).micro > outputs/$(TESTARGS).out
	diff -B -b outputs/$(TESTARGS).out ../step2/testcases/output/$(TESTARGS).out

test3:
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro Step3_testfiles/step3/EE468/input/$(TESTARGS).micro > outputs/$(TESTARGS).out
	diff -B -b -y outputs/$(TESTARGS).out Step3_testfiles/step3/EE468/output/$(TESTARGS).out

test4: compiler
	 java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step4/input/$(TESTARGS).micro 

test5 : compiler
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step5/EE468/input/$(TESTARGS).micro 

test6 : compiler
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step6/input/$(TESTARGS).micro 

test7 : compiler
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step6/input/$(TESTARGS).micro 

clean:
	rm -rf classes build

.PHONY: all group compiler clean
