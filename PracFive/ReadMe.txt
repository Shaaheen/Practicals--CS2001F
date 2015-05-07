To run:
	-use makefile in PracFive folder
	-run the UserInterface to use the hashtable classes to search for words in a dictionary
	(java UserInterface)
	-run the EfficiencyCheck class to check how well the hashtables perform against each other - has Load Performance and Search Performance
	(java EfficiencyCheck) 

All java files are in PracFiveSource as well as the test folder

Evaluation is in the MainDocument pdf

EfficiencyCheck : Contains both the Search and Load Performance tests in it with a User Interface for Questions 3.2.1 and 3.2.2

Junit tests and Jacocoa Coverage is in the test folder under PracFiveSource

- There are 5 Junit tests for each implemented class
- The Hashtable tests inherit from each other as they are mostly the same with a few exceptions
- *Inheritance in Junit runs all the tests from its parents class aswell
- There is also Word and ChainedWord Tests which uses inheritance tests also
- Jacocoa Coverage is done on all Junit tests cumatively - 100% coverage