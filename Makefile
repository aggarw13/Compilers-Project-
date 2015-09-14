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
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro testfiles/nested.micro > outputs/nested.out
	#diff -B -b -y exp_outputs/nested.out outputs/nested.out
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro testfiles/loop.micro > outputs/loop.out
	#diff -B -b exp_outputs/loop.out outputs/loop.out
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro testfiles/sqrt.micro > outputs/sqrt.out
	#diff -B -b exp_outputs/fibonacci.out outputs/fibonacci.out

test2: compiler
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test1.micro > outputs/test1.out
	#diff -B -b outputs/test1.out ../step2/testcases/output/test1.out
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test2.micro > outputs/test2.out
	#diff -B -b outputs/test2.out ../step2/testcases/output/test2.out
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test3.micro > outputs/test3.out
	#iff -B -b outputs/test3.out ../step2/testcases/output/test3.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test4.micro > outputs/test4.out
	diff -B -b outputs/test4.out ../step2/testcases/output/test4.out
	#java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test5.micro > outputs/test5.out
	#diff -B -b outputs/test5.out ../step2/testcases/output/test5.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test6.micro > outputs/test6.out
	diff -B -b outputs/test6.out ../step2/testcases/output/test6.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test8.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test9.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test10.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out
	java -cp lib/antlr-4.5.1-complete.jar:classes/ Micro ../step2/testcases/input/test7.micro > outputs/test7.out
	diff -B -b outputs/test7.out ../step2/testcases/output/test7.out


clean:
	rm -rf classes build

.PHONY: all group compiler clean
