#include <iostream>
#include <fstream>
#include <stdlib.h>

#define SIZE 20*1024
using namespace std;

int main(int argc, char* argv[]){

	if (argc < 2) {
		cout << "Usage: " << argv[0] << " <filename>" << endl;
		return 0;
	}

	char content[SIZE];
	ifstream myfile;

	myfile.open(argv[1], ios::in | ios::binary);
	
	long read = 0;
	while (myfile.good()){
		myfile.read(content, SIZE);
		read += myfile.gcount();
	}
	cout << "C++: Read " << read << " bytes" << endl;
	myfile.close();

	return 0;
}
