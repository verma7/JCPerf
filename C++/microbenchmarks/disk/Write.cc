#include <iostream>
#include <stdlib.h>
#include <fstream>
using namespace std;

int main(int argc,char* argv[]){
	long size;
	ofstream myfile;

	if (argc < 3) {
		cout << "Usage: " << argv[0] << " <filename> <size>" << endl;
		return 0;
	}
	myfile.open(argv[1], ios::binary | ios::out);
	size = atol(argv[2]);

	long write = 0;
	for(long i = 0; i < size/sizeof(int); i++) {
		int x = rand();
		myfile.write((char*)&x, sizeof(int));
		write += sizeof(int);
	}
	cout << "C++: Written " << write << " bytes." << endl;
	myfile.close();  
	sync();
	return 0;
}
